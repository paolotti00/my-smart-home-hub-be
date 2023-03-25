package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.ICommandMapper;
import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.CommandRepository;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public abstract class DeviceAbstractServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceAbstractServiceImpl.class);

    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    ICommandMapper commandMapper;
    @Autowired
    CommandRepository commandRepository;



    @Override
    public Device create(Device device) throws MissingFieldException {
        return null;
    }

    @Override
    public Device getDevice(String deviceId) throws DeviceNotExistsException, ValidationException {
        return null;
    }

    @Override
    public List<Device> getDevicesByUserId(String userId) throws ValidationException {
        return null;
    }

    @Override
    public List<Device> getDevicesByRoomId(String roomId) throws ValidationException, RoomNotExistsException {
        return null;
    }

    // action
    @Override
    public List<ExtraActionCommandData> getSupportedExtraActions(String deviceId) throws BrandNotSupportedException, ValidationException, DeviceNotExistsException {
        logger.debug("getting supported ExtraAction for device {}",deviceId);
        Device device = getDevice(deviceId);
        List<ExtraActionCommandData> extraActionCommandDataList = device.getSupportedExtraActions();
        logger.info("supported ExtraAction retrieved for device {} : {}",deviceId,extraActionCommandDataList);
        return extraActionCommandDataList;
    }

    // command
     void saveCommandInDb(Command command, String deviceId, String deviceThingId, String roomId, CommandDestinationTypeEnum commandDestinationTypeEnum) {
        // save sent command in db
        logger.info("saving in db commandId {}, deviceId{}, groupId {}", command.getCommandId(), deviceId, roomId);
        command.setStatusEnum(CommandStatusEnum.PENDING);
        command.setCreationDate(LocalDateTime.now());
        switch (commandDestinationTypeEnum){
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
        logger.info("saved in db commandId {}, deviceId{} , groupId {} with id {}", command.getCommandId(), deviceId, roomId,commandEntity.getId());
    }

    // light
    @Override
    abstract public void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws GenericException;

    @Override
    abstract public void setLightColor(String userId, String deviceId, String rgbColor) throws GenericException ;

}
