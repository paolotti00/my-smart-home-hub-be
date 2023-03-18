package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.RoomNotExistsException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupServiceImpl implements IGroupService {
    @Autowired
    IDeviceService deviceService;
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Override
    public void switchAllLightsByRoom(String userId, String groupId, OnOffStatusEnum desiredStatus) throws RoomNotExistsException, ValidationException {
        logger.info("switching device lights by group : userId {} groupId {} desiredStatus {}", userId, groupId, desiredStatus);
        // todo retrieve the user
        // check if it have the permission to do something
        List<Device> devices = deviceService.getDevicesByRoomId(groupId);
        if (devices != null && !devices.isEmpty()) {
            devices.forEach(device -> {
                try {
                    deviceService.switchAllLights(userId,device.getId(), desiredStatus);
                } catch (BrandNotSupportedException e) {
                    logger.error("failed to switch off all light of device with id {} because:{}", device.getId(), e.getMessage());
                } catch (DeviceNotExistsException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }
}
