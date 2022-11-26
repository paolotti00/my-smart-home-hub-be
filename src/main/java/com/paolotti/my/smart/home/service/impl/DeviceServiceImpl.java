package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceActionsSchema;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.IDeviceGroupCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.GroupDeviceEntity;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    IDeviceCustomRepository deviceCustomRepository;
    @Autowired
    IDeviceGroupCustomRepository deviceGroupCustomRepository;
    @Autowired
    IDeviceMapper deviceMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceServiceImpl.class);

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
        GroupDeviceEntity groupDeviceEntity = deviceGroupCustomRepository.findById(groupId);
        if(groupDeviceEntity == null){
            throw new GroupNotExistsException(groupId);
        }
        if (groupDeviceEntity.getDevices().isEmpty()) {
            logger.warn("no device in group id {} found", groupId);
        } else {
            logger.debug("converting deviceEntity to device model");
            devices = deviceMapper.toModels(groupDeviceEntity.getDevices());
        }
        logger.info("retrieved {} devices in the group with id {} and name",devices.size(),groupId);
        return devices;
    }
}
