package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.ICommandMapper;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.model.Device;
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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class DeviceAbstractServiceImpl implements IDeviceService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceAbstractServiceImpl.class);

    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    ICommandMapper commandMapper;
    @Autowired
    CommandRepository commandRepository;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    IDeviceMapper deviceMapper;



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

}
