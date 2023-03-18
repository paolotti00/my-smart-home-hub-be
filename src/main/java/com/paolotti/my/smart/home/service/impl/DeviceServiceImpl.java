package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.enums.*;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.ICommandMapper;
import com.paolotti.my.smart.home.mapper.IDeviceStatusMapper;
import com.paolotti.my.smart.home.mapper.deprecated.IDeviceMapper;
import com.paolotti.my.smart.home.model.*;
import com.paolotti.my.smart.home.repository.CommandRepository;
import com.paolotti.my.smart.home.repository.RoomRepository;
import com.paolotti.my.smart.home.repository.DeviceRepository;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import com.paolotti.my.smart.home.service.*;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    IValidationHelperService validationHelperService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    ICommandMapper commandMapper;
    @Autowired
    IDeviceStatusMapper deviceStatusMapper;
    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    IMqttMessagingService mqttMessagingService;
    @Autowired
    IDeviceComponentService deviceComponentService;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);
    private static final String DEVICE_STATUS_UPDATED_WEBSOCKET_TOPIC = "/topic/device/{deviceId}/status";

    @Override
    public Device create(Device device) throws MissingFieldException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: creation device started, device to create {}", methodName, device);
        Device finalDevice = device;
        // validation
        ArrayList<ValidationHelperObject> toValidateItems = new ArrayList<>();
        toValidateItems.add(new ValidationHelperObject<String>("name", finalDevice.getName(), ValidationHelperObject.ValidationType.NOT_NULL));
        toValidateItems.add(new ValidationHelperObject<Object>("brand", finalDevice.getBrand(), ValidationHelperObject.ValidationType.NOT_NULL));
        validationHelperService.validate(toValidateItems);
        // creation
        device.setCreationDate(LocalDateTime.now());
        device.setInstallationStatus(DeviceInstallationStatusEnum.TO_ACTIVATE);
        DeviceEntity deviceEntity = deviceMapper.toEntity(device);
        deviceEntity = deviceRepository.save(deviceEntity);
        logger.info("entity saved with id {}", deviceEntity.getId());
        logger.debug("the entity saved is {}", deviceEntity);
        device = deviceMapper.toModel(deviceEntity);
        return device;
    }

    @Override
    public Device getDevice(String deviceId) throws DeviceNotExistsException, ValidationException {
        logger.info("retrieving device with id {}", deviceId);
        if (StringUtils.isEmpty(deviceId)) {
            throw new ValidationException("deviceId cannot be null or empty");
        }
        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findById(deviceId);
        if (!deviceEntityOpt.isPresent()) {
            logger.warn("no active device found with {} id", deviceId);
            throw new DeviceNotExistsException(deviceId);
        }
        DeviceEntity deviceEntity = deviceEntityOpt.get();
        logger.info("device {} retrieved, converting to model", deviceId);
        Device device = deviceMapper.toModel(deviceEntity);
        logger.info("device converted to {}", device);
        return device;
    }

    @Override
    public List<Device> getDevicesByUserId(String userId) throws ValidationException {
        logger.info("retrieving device for user with id {}", userId);
        List<Device> devices = new ArrayList<>();
        if (StringUtils.isEmpty(userId)) {
            throw new ValidationException("userId cannot be null or empty");
        }
        Optional<List<DeviceEntity>> devicesOpt = deviceRepository.findByUsersOwnersIdsContaining(userId);
        if (devicesOpt.isPresent()) {
            List<DeviceEntity> deviceEntities = devicesOpt.get();
            devices = deviceMapper.toModelList(deviceEntities);
        } else {
            logger.warn("no devices found for user with id {}", userId);
        }

        logger.info("retrieved {} devices for user with id {}", devices.size(), userId);
        return devices;
    }

    @Override
    public void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema) {

    }

    @Override
    public List<Action> getSupportedActionsSchemas(String deviceId) {
        return null;
    }

    @Override
    public void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema) {

    }

    @Override
    public Device getActiveDeviceById(String deviceId) throws DeviceNotExistsException {
        logger.info("retrieving active device with id {}", deviceId);
        DeviceEntity deviceEntity = deviceRepository.findActiveById(deviceId);
        if (deviceEntity == null) {
            logger.warn("no active device found with {} id", deviceId);
            throw new DeviceNotExistsException(deviceId);
        }
        logger.info("active device {} retrieved, converting to model", deviceId);
        Device device = deviceMapper.toModel(deviceEntity);
        logger.info("active device converted to {}", device);
        return device;
    }

    @Override
    public List<Device> getDevicesByRoomId(String roomId) throws RoomNotExistsException {
        // todo pt check if this user is the owner of this room or if can be read it
        logger.info("retrieving devices of the room with id {}", roomId);
        List<Device> devices = new ArrayList<>();
        logger.info("checking if room with id {} exists", roomId);
        Optional<RoomEntity> roomEntityOpt = roomRepository.findById(roomId);
        if (!roomEntityOpt.isPresent()) {
            throw new RoomNotExistsException(roomId);
        }
        RoomEntity roomEntity = roomEntityOpt.get();
        if (roomEntity.getDevices().isEmpty()) {
            logger.warn("no device in room id {} found", roomId);
        } else {
            logger.debug("converting deviceEntity to device model");
            devices = deviceMapper.toModelList(roomEntity.getDevices());
        }
        logger.info("retrieved {} devices in the room with id {} and name", devices.size(), roomId);
        return devices;
    }

    // command
    @Override
    public void sendMqttCommandToAll(String topic, String payloadToEncapsulate) throws GenericException {
        sendMqttCommand(topic, payloadToEncapsulate, null, null);
    }

    @Override
    public void sendMqttCommandToDevice(String topic, String payloadToEncapsulate, Device device) throws GenericException {
        sendMqttCommand(topic, payloadToEncapsulate, device, null);
    }

    @Override
    public void sendMqttCommandToDeviceGroup(String topic, String payloadToEncapsulate, DeviceGroup deviceGroup) throws GenericException {
        sendMqttCommand(topic, payloadToEncapsulate, null, deviceGroup);
    }

    private void sendMqttCommand(String topic, String payloadToEncapsulate, Device device, DeviceGroup deviceGroup) throws GenericException {
        logger.info("sending mqtt command on topic {} with payloadToEncapsulate {} to deviceId {}", topic, payloadToEncapsulate, device != null ? device.getId() : "all");
        String commandId = null;
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            commandId = String.valueOf(System.currentTimeMillis());
            Command command = new Command();
            command.setCommandId(commandId);
            command.setData(payloadToEncapsulate);
            payload = objectMapper.writeValueAsString(commandMapper.toDto(command));
            mqttMessagingService.publish(topic, payload, 1, true);
            saveCommandInDb(command, device, deviceGroup);
            // todo save command on db - create entity
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("sent - mqtt commandId {} on topic {} with payload {} to deviceId {}", commandId, topic, payload, device != null ? device.getId() : "all");
    }

    private void saveCommandInDb(Command command, Device device, DeviceGroup deviceGroup) {
        // save sent command in db
        logger.info("saving in db commandId {}, deviceId{}, groupId {}", command.getCommandId(), device != null ? device.getId() : null, deviceGroup != null ? deviceGroup.getId() : null);
        command.setStatusEnum(CommandStatusEnum.PENDING);
        command.setCreationDate(LocalDateTime.now());
        if (device != null) {
            // is a command to specific device
            command.setDeviceId(device.getId());
            command.setThingId(device.getThingId());
            command.setDestinationType(CommandDestinationTypeEnum.TO_DEVICE);
        } // todo check other case
        CommandEntity commandEntity = commandRepository.save(commandMapper.toEntity(command));
        logger.info("saved in db commandId {}, deviceId{} , groupId {} with id {}", command.getCommandId(), device != null ? device.getId() : null, deviceGroup != null ? deviceGroup.getId() : null, commandEntity.getId());
    }

    // status

    @Override
    public void updateDeviceStatusFromAckReceived(AckCommand ackCommand) throws ValidationException, DeviceNotExistsException {
        logger.info("updating status of device id {} by ack received {}", ackCommand.getThingId(), ackCommand);
        // validation
        logger.info("validation started");
        if (ackCommand.getCommandId() == null || !StringUtils.hasText(ackCommand.getCommandId())) {
            throw new ValidationException("commandId is null or empty. commandId =" + ackCommand.getCommandId());
        }
        if (ackCommand.getThingId() == null || !StringUtils.hasText(ackCommand.getThingId())) {
            throw new ValidationException("deviceId is null or empty. thinkId =" + ackCommand.getThingId());
        }
        if (ackCommand.getAck() == null) {
            throw new ValidationException("command ack is null");
        }
        if (ackCommand.getDeviceStatus() == null) {
            throw new ValidationException("deviceStatus is null");
        }
        logger.info("validation finished. all is ok.");
        // execution
        // update command status on db
        updateCommandStatusOnDb(ackCommand);
        // update device status on db
        updateDeviceStatus(ackCommand.getThingId(), ackCommand.getDeviceStatus());
        logger.info("status of device id {} correctly updated", ackCommand.getThingId());
    }

    @Override
    public void handleDeviceStatusFromPingReceived(PingDeviceStatus pingDeviceStatus) throws ValidationException, DeviceNotExistsException {
        logger.info("handling update status of device id {} by ping received {}", pingDeviceStatus.getThingId(), pingDeviceStatus);

        // update device status on db
        updateDeviceStatus(pingDeviceStatus.getThingId(), pingDeviceStatus.getDeviceStatus());
        // sending websocket update
        sendUpdatedDeviceStatusByWebsocket(pingDeviceStatus.getThingId(), pingDeviceStatus.getDeviceStatus());

        logger.info("update status of device id {} by ping correctly handled", pingDeviceStatus.getThingId());
    }

    private void updateDeviceStatus(String thingId, DeviceStatus deviceStatus) throws DeviceNotExistsException {
        logger.info("updating status of device id {} by ping received {}", thingId, deviceStatus);
        // getting device from db
        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByThingId(thingId);
        if (!deviceEntityOpt.isPresent()) {
            logger.error("device with thingId : {} not exists. can't update component status", thingId);
            throw new DeviceNotExistsException("thingId: " + thingId);
        }
        DeviceEntity deviceEntity = deviceEntityOpt.get();
        // updating components status
        deviceComponentService.updateComponentsStatus(deviceEntity, deviceStatus.getComponents());
        deviceEntity.setUpdateDate(LocalDateTime.now());
        deviceEntity.setStatus(DeviceConnectionStatusEnum.ONLINE);
        logger.info("device id {} set update time and connection status {}", deviceEntity.getId(), DeviceConnectionStatusEnum.ONLINE);
        logger.info("status of device id {} correctly updated", thingId);
        deviceRepository.save(deviceEntity);
    }

    private void sendUpdatedDeviceStatusByWebsocket(String thingId, DeviceStatus deviceStatus) throws DeviceNotExistsException {
        logger.info("sending, by websocket, for thingId {} updateStatus {}", thingId, deviceStatus);
        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByThingId(thingId);
        if (!deviceEntityOpt.isPresent()) {
            logger.error("device with thingId : {} not exists. can't update component status", thingId);
            throw new DeviceNotExistsException("thingId: " + thingId);
        }
        DeviceEntity deviceEntity = deviceEntityOpt.get();
        DeviceStatusDto deviceStatusDto = deviceStatusMapper.toDto(deviceStatus);
        simpMessagingTemplate.convertAndSend(DEVICE_STATUS_UPDATED_WEBSOCKET_TOPIC.replace("{deviceId}", deviceEntity.getId().toHexString()), deviceStatusDto);
        // logger.info("updateStatus {} by websocket for thingId {} was sent correctly", deviceStatusDto, thingId); // todo understand why in the log just DeviceComponentDto is logged and not DeviceComponentLightDto
        logger.info("updateStatus by websocket for thingId {} was sent correctly", thingId);
    }

    private void updateCommandStatusOnDb(AckCommand ackCommand) {
        // getting command saved on db and update it
        Optional<CommandEntity> commandEntityOpt = commandRepository.findByCommandId(ackCommand.getCommandId());
        if (commandEntityOpt.isPresent()) {
            CommandEntity commandEntity = commandEntityOpt.get();
            logger.info("for commandId {} command db entity id {} was retrieved", ackCommand.getCommandId(), commandEntity.getId());
            if (ackCommand.getAck() == ResultStatusEnum.OK) {
                commandEntity.setStatusEnum(CommandStatusEnum.DONE);
            } else {
                commandEntity.setStatusEnum(CommandStatusEnum.ERROR);
            }
            commandEntity.setUpdateDate(LocalDateTime.now());
            commandRepository.save(commandEntity);
            logger.info("command entity id {} status correctly updated", commandEntity.getId());
        } else {
            logger.warn("no command db entity found for command id {}", ackCommand.getCommandId());
        }
    }


    // light
    @Override
    public void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException {
        logger.info("switching device lights by device : userId {} deviceId {} desiredStatus {}", userId, deviceId, desiredStatus);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = getActiveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        IDeviceByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        switch (desiredStatus) {
            case ON:
                deviceLightByBrandService.switchOn(device);
                logger.info("device {} switched ON", device.getId());
                break;
            case OFF:
                deviceLightByBrandService.switchOff(device);
                logger.info("device {} switched OFF", device.getId());
                break;
        }
    }

    @Override
    public void setColor(String userId, String deviceId, String rgbColor) throws BrandNotSupportedException, DeviceNotExistsException, GenericException {
        logger.info("setting device lights color to : userId {} deviceId {} colorRgb {}", userId, deviceId, rgbColor);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = getActiveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        IDeviceByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.setColor(device, rgbColor);
        logger.info("colorRgb {} correctly set", rgbColor);
    }

    @Override
    public void doAction(String userId, String deviceId, Action action) throws BrandNotSupportedException, DeviceNotExistsException, GenericException {
        logger.info("sending device action to do : userId {} deviceId {} lightEffectMessage {}", userId, deviceId, action);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = getActiveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        IDeviceByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.doEffect(device, action);
        logger.info("correctly sent action to do : userId {} deviceId {} lightEffectMessage {}", userId, deviceId, action);
    }
}
