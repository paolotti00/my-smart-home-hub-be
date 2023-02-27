package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.ICommandMapper;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.*;
import com.paolotti.my.smart.home.repository.ICommandCustomRepository;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.IDeviceGroupCustomRepository;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.DeviceGroupEntity;
import com.paolotti.my.smart.home.service.IDeviceByBrandService;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IMqttMessagingService;
import com.paolotti.my.smart.home.service.IValidationHelperService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    IDeviceCustomRepository deviceCustomRepository;
    @Autowired
    IDeviceGroupCustomRepository deviceGroupCustomRepository;
    @Autowired
    IValidationHelperService validationHelperService;
    @Autowired
    IDeviceMapper deviceMapper;
    @Autowired
    ICommandMapper commandMapper;
    @Autowired
    ICommandCustomRepository commandCustomRepository;
    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    IMqttMessagingService mqttMessagingService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Override
    public Device create(Device device) throws  MissingFieldException {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("{}: creation device started, device to create {}",methodName,device);
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
        deviceEntity = deviceCustomRepository.save(deviceEntity);
        logger.info("entity saved with id {}",deviceEntity.getId());
        logger.debug("the entity saved is {}",deviceEntity);
        device = deviceMapper.toModel(deviceEntity);
        return device;
    }

    @Override
    public void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema) {

    }

    @Override
    public ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(String deviceId) {
        return null;
    }

    @Override
    public void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema) {

    }

    @Override
    public Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException {
        logger.info("retrieving device with id {}", deviceId);
        DeviceEntity deviceEntity = deviceCustomRepository.findById(deviceId);
        if (deviceEntity == null) {
            logger.warn("no device found with {} id", deviceId);
            throw new DeviceNotExistsException(deviceId);
        }
        logger.info("device {} retrieved, converting to model", deviceId);
        Device device = deviceMapper.toModel(deviceEntity);
        logger.info("device converted to {}", device);
        return device;
    }

    @Override
    public ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException {
        // todo pt check if this user is the owner of this group or if can be read it
        logger.info("retrieving devices of the group with id {}", groupId);
        ArrayList<Device>devices = new ArrayList<>();
        logger.info("checking if group with id {} exists",groupId);
        DeviceGroupEntity deviceGroupEntity = deviceGroupCustomRepository.findById(groupId);
        if(deviceGroupEntity == null){
            throw new GroupNotExistsException(groupId);
        }
        if (deviceGroupEntity.getDevices().isEmpty()) {
            logger.warn("no device in group id {} found", groupId);
        } else {
            logger.debug("converting deviceEntity to device model");
            devices = deviceMapper.toModels(deviceGroupEntity.getDevices());
        }
        logger.info("retrieved {} devices in the group with id {} and name",devices.size(),groupId);
        return devices;
    }
    // command
    @Override
    public void sendMqttCommandToAll(String topic, String payloadToEncapsulate) throws GenericException {
        sendMqttCommand(topic,payloadToEncapsulate,null,null);
    }
    @Override
    public void sendMqttCommandToDevice(String topic, String payloadToEncapsulate, Device device) throws GenericException {
        sendMqttCommand(topic,payloadToEncapsulate,device,null);
    }
    @Override
    public void sendMqttCommandToDeviceGroup(String topic, String payloadToEncapsulate, DeviceGroup deviceGroup) throws GenericException {
        sendMqttCommand(topic,payloadToEncapsulate,null,deviceGroup);
    }
    private void sendMqttCommand (String topic, String payloadToEncapsulate, Device device, DeviceGroup deviceGroup) throws GenericException {
        logger.info("sending mqtt command on topic {} with payloadToEncapsulate {} to deviceId {}",topic,payloadToEncapsulate,device!=null?device.getId():"all");
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
            saveCommandInDb(command,device,deviceGroup);
            // todo save command on db - create entity
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("sent - mqtt commandId {} on topic {} with payload {} to deviceId {}",commandId,topic,payload,device!=null?device.getId():"all");
    }
    private void saveCommandInDb(Command command,Device device, DeviceGroup deviceGroup){
        // save sent command in db
        logger.info("saving in db commandId {}, deviceId{}, groupId {}",command.getCommandId(),device!=null?device.getId():null,deviceGroup!=null?deviceGroup.getId():null);
        command.setStatusEnum(CommandStatusEnum.PENDING);
        command.setCreationDate(LocalDateTime.now());
        if(device!=null){
            // is a command to specific device
            command.setDeviceId(device.getId());
            command.setThingId(device.getThingId());
            command.setDestinationType(CommandDestinationTypeEnum.TO_DEVICE);
        } // todo check other case
        CommandEntity commandEntity = commandCustomRepository.save(commandMapper.toEntity(command));
        logger.info("saved in db commandId {}, deviceId{} , groupId {} with id {}",command.getCommandId(),device!=null?device.getId():null,deviceGroup!=null?deviceGroup.getId():null,commandEntity.getId());
    }
    // light
    @Override
    public void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException {
        logger.info("switching device lights by device : userId {} deviceId {} desiredStatus {}", userId, deviceId, desiredStatus);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = retrieveDeviceById(deviceId);
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
    public void setColor(String userId, String deviceId, ColorRgb colorRgb) throws BrandNotSupportedException, DeviceNotExistsException, GenericException {
        logger.info("setting device lights color to : userId {} deviceId {} colorRgb {}", userId, deviceId, colorRgb);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = retrieveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        IDeviceByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.setColor(device,colorRgb);
        logger.info("colorRgb {} correctly set", colorRgb);
    }

    @Override
    public void doAction(String userId, String deviceId, Action action) throws BrandNotSupportedException, DeviceNotExistsException, GenericException {
        logger.info("sending device action to do : userId {} deviceId {} lightEffectMessage {}", userId, deviceId, action);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = retrieveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        IDeviceByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.doEffect(device, action);
        logger.info("correctly sent action to do : userId {} deviceId {} lightEffectMessage {}", userId, deviceId, action);
    }
}
