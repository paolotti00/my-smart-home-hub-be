package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.service.IDeviceLightByBrandService;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeviceLightWrapperServiceImpl implements IDeviceLightService {
    @Autowired
    IBeanFactoryService beanFactoryService;
    @Autowired
    IDeviceService deviceService;
    @Autowired
    IDeviceCustomRepository deviceCustomRepository;
    @Autowired
    IDeviceMapper deviceMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceLightWrapperServiceImpl.class);

    @Override
    public void switchAllLightsByDevice(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException {
        logger.info("switching device lights by device : userId {} deviceId {} desiredStatus {}", userId, deviceId, desiredStatus);
        // todo retrieve the user
        // check if have the permission to do something
        Device device = null;
        device = deviceService.retrieveDeviceById(deviceId);
        logger.info("device retrieved {}", device);
        switchAllLightsByDevice(device, desiredStatus);
    }


    @Override
    public void switchAllLightsByGroup(String userId, String groupId, OnOffStatusEnum desiredStatus) throws GroupNotExistsException {
        logger.info("switching device lights by group : userId {} groupId {} desiredStatus {}", userId, groupId, desiredStatus);
        // todo retrieve the user
        // check if it have the permission to do something
        ArrayList<Device> devices = deviceService.retrieveDevicesByGroupId(groupId);
        if (devices != null && !devices.isEmpty()) {
            devices.forEach(device -> {
                IDeviceLightByBrandService deviceLightByBrandService = null;
                try {
                    switchAllLightsByDevice(device, desiredStatus);
                } catch (BrandNotSupportedException e) {
                    logger.error("failed to switch off all light of device with id {} because:{}", device.getId(), e.getMessage());
                }
            });
        }

    }

    private void switchAllLightsByDevice(Device device, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException {
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        switch (desiredStatus) {
            case ON:
                deviceLightByBrandService.switchOn(device);
                logger.info("device {} switched ON", device.getId());
                break;
            case OFF:
                deviceLightByBrandService.switchOff(device);
                logger.info("device {} switched OFF", device.getId());
        }
    }
}
