package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.dto.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import com.paolotti.my.smart.home.enums.ConnectionStatusEnum;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.mapper.ICommandMapper;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.mapper.IDeviceStatusMapper;
import com.paolotti.my.smart.home.mapper.ISensorMapper;
import com.paolotti.my.smart.home.model.*;
import com.paolotti.my.smart.home.repository.CommandRepository;
import com.paolotti.my.smart.home.repository.DeviceRepository;
import com.paolotti.my.smart.home.repository.RoomRepository;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class DeviceAbstractServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceAbstractServiceImpl.class);
    private static final String DEVICE_STATUS_UPDATED_WEBSOCKET_TOPIC = "/topic/device/{deviceId}/status"; // todo move in properties file

    @Autowired
    IBeanFactoryDeviceService beanFactoryService;
    @Autowired
    ICommandMapper commandMapper;
    @Autowired
    IDeviceStatusMapper deviceStatusMapper;
    @Autowired
    ISensorMapper sensorMapper;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public Device create(Device device) throws MissingFieldException {
        return null;
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
    public List<Device> getDevicesByRoomId(String roomId) throws ValidationException, RoomNotExistsException {
        // todo pt check if this user is the owner of this room or if can be read it
        logger.info("retrieving devices of the room with id {}", roomId);
        List<Device> devices = new ArrayList<>();
        logger.info("checking if room with id {} exists", roomId);
        Optional<RoomEntity> roomEntityOpt = roomRepository.findById(roomId);
        if (!roomEntityOpt.isPresent()) {
            throw new RoomNotExistsException(roomId);
        }
        Optional<List<DeviceEntity>> deviceEntityListOpt = deviceRepository.findByRoomId(roomId);
        if (!deviceEntityListOpt.isPresent()) {
            logger.warn("no device in room id {} found", roomId);
        } else {
            logger.debug("converting deviceEntity to device model");
            devices = deviceMapper.toModelList(deviceEntityListOpt.get());
        }
        logger.info("retrieved {} devices in the room with id {} and name", devices.size(), roomId);
        return devices;
    }

    // action
    @Override
    public List<ExtraActionCommandData> getSupportedExtraActions(String deviceId) throws BrandNotSupportedException, ValidationException, DeviceNotExistsException {
        logger.debug("getting supported ExtraAction for device {}", deviceId);
        Device device = getDevice(deviceId);
        List<ExtraActionCommandData> extraActionCommandDataList = device.getSupportedExtraActions();
        logger.info("supported ExtraAction retrieved for device {} : {}", deviceId, extraActionCommandDataList);
        return extraActionCommandDataList;
    }

    // command
    void saveCommandInDb(Command command, String deviceId, String deviceThingId, String roomId, CommandDestinationTypeEnum commandDestinationTypeEnum) {
        // save sent command in db
        logger.info("saving in db commandId {}, deviceId{}, groupId {}", command.getCommandId(), deviceId, roomId);
        command.setStatusEnum(CommandStatusEnum.PENDING);
        command.setCreationDate(LocalDateTime.now());
        switch (commandDestinationTypeEnum) {
            case TO_DEVICE:
                // is a command to specific device
                command.setDeviceId(deviceId);
                command.setThingId(deviceThingId);
                command.setDestinationType(CommandDestinationTypeEnum.TO_DEVICE);
                break;
            case TO_ROOM:
                // todo
        }
        CommandEntity commandEntity = commandRepository.save(commandMapper.toEntity(command));
        logger.info("saved in db commandId {}, deviceId{} , groupId {} with id {}", command.getCommandId(), deviceId, roomId, commandEntity.getId());
    }

    void sendUpdatedDeviceStatusByWebsocket(String thingId, DeviceStatus deviceStatus) throws DeviceNotExistsException {
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

    void updateCommandStatusOnDb(AckCommand ackCommand) {
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

    void updateDeviceStatus(String thingId, DeviceStatus deviceStatus) throws DeviceNotExistsException {
        logger.info("updating status of device id {} by status received {}", thingId, deviceStatus);
        // getting device from db
        Optional<DeviceEntity> deviceEntityOpt = deviceRepository.findByThingId(thingId);
        if (!deviceEntityOpt.isPresent()) {
            logger.error("device with thingId : {} not exists. can't update component status", thingId);
            throw new DeviceNotExistsException("thingId: " + thingId);
        }
        DeviceEntity deviceEntity = deviceEntityOpt.get();
        // updating components status
        updateComponentsStatus(deviceEntity, deviceStatus.getSensors(), deviceStatus.getLeds());
        deviceEntity.setUpdateDate(LocalDateTime.now());
        deviceEntity.setConnectionStatus(ConnectionStatusEnum.ONLINE);
        logger.info("device id {} set update time and connection status {}", deviceEntity.getId(), ConnectionStatusEnum.ONLINE);
        logger.info("status of device id {} correctly updated", thingId);
        deviceRepository.save(deviceEntity);
    }

    private void updateComponentsStatus(DeviceEntity deviceEntity, List<Sensor> updatedSensors, Map<Integer, int[]> updatedLeds) {
        // sensors
        if (updatedSensors == null || updatedSensors.isEmpty()) {
            logger.debug("no sensors to update");
        } else {
            updateSensorsStatus(deviceEntity,updatedSensors);
        }
        // leds
        if (updatedLeds == null || updatedLeds.isEmpty()) {
            logger.debug("no leds to update");
        } else {
            updateLedStatus(deviceEntity,updatedLeds);
        }
    }
    private void updateSensorsStatus(DeviceEntity deviceEntity,List<Sensor> updatedSensors){
        if (deviceEntity.getSensors().isEmpty()) {
            // all sensors don't exist, so we will add and save all
            deviceEntity.setSensors(sensorMapper.toEntityList(updatedSensors));
            logger.info("deviceEntity {} doesn't have any sensors all of updateSensors will be created", deviceEntity.getId());
        } else {
            updatedSensors.forEach(sensor -> {
                Optional<DeviceEntity.Sensor> deviceSensorEntityOpt = deviceEntity.getSensors().stream()
                        .filter(sensorEntity -> sensorEntity.getId().equals(sensor.getId()))
                        .findFirst();
                if (!deviceSensorEntityOpt.isPresent()) {
                    // the sensor doesn't exist , will create
                    logger.info("sensor with id {} doesn't exist on deviceEntity {}, will be created", sensor.getId(), deviceEntity.getId());
                    sensor.setTimestamp(LocalDateTime.now());
                    deviceEntity.getSensors().add(sensorMapper.toEntity(sensor));
                } else {
                    // the sensor doesn't exist , will be updated
                    logger.info("sensor with id {} doesn't exist on deviceEntity {}, will be updated", sensor.getId(), deviceEntity.getId());
                    DeviceEntity.Sensor sensorEntity = deviceSensorEntityOpt.get();
                    sensorEntity.setValue(sensor.getValue());
                    sensorEntity.setTimestamp(LocalDateTime.now());
                }
            });
        }
    }
    private void updateLedStatus(DeviceEntity deviceEntity,Map<Integer, int[]> updatedLeds){
        if (deviceEntity.getLeds().isEmpty()) {
            // all sensors don't exist, so we will add and save all
            logger.info("deviceEntity {} doesn't have any leds all of updatedLeds will be created", deviceEntity.getId());
        } else {
            logger.info("deviceEntity {} already have leds all of them  will be replaced by updatedLeds", deviceEntity.getId());
        }
        deviceEntity.setLeds(updatedLeds);
    }
}
