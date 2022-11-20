package com.paolotti.my.smart.home.service.impl;

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
    public void switchOnAllLightsByDevice(String userId, String deviceId) throws BrandNotSupportedException, DeviceNotExistsException {
        // todo log
        // todo retrieve the user
        // check if have the permission to do something
        // todo retrieve the device
        Device device = deviceService.retrieveDeviceById(deviceId);
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.switchOn(device);
    }

    @Override
    public void switchOffAllLightsByDevice(String userId, String deviceId) throws BrandNotSupportedException, DeviceNotExistsException {
        // todo log
        // todo retrieve the user
        // check if have the permission to do something
        // todo retrieve the device
        Device device = null;
        device = deviceService.retrieveDeviceById(deviceId);
        switchOffAllLightsByDevice(device);


    }

    @Override
    public void switchOnAllLightsByGroup(String userId, String groupId) throws GroupNotExistsException {
        // todo log
        // todo retrieve the user
        // check if it have the permission to do something
        // todo retrieve the device
        ArrayList<Device> devices = deviceService.retrieveDevicesByGroupId(groupId);
        if(devices!=null && !devices.isEmpty()){
            devices.forEach(device -> {
                IDeviceLightByBrandService deviceLightByBrandService = null;
                try {
                    switchOnAllLightsByDevice(device);
                } catch (BrandNotSupportedException e) {
                    logger.error("failed to switch on all light of device with id {} because:{}",device.getId(),e.getMessage());
                }
            });
        }

    }

    @Override
    public void switchOffAllLightsByGroup(String userId, String groupId) throws GroupNotExistsException {
        // todo log
        // todo retrieve the user
        // check if it have the permission to do something
        // todo retrieve the device
        ArrayList<Device> devices = deviceService.retrieveDevicesByGroupId(groupId);
        if(devices!=null && !devices.isEmpty()){
            devices.forEach(device -> {
                IDeviceLightByBrandService deviceLightByBrandService = null;
                try {
                    switchOffAllLightsByDevice(device);
                } catch (BrandNotSupportedException e) {
                    logger.error("failed to switch off all light of device with id {} because:{}",device.getId(),e.getMessage());
                }
            });
        }

    }

    private void switchOffAllLightsByDevice(Device device) throws BrandNotSupportedException {
        logger.info("switch off device with id {}",device.getId());
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.switchOff(device);
    }
    private void switchOnAllLightsByDevice(Device device) throws BrandNotSupportedException {
        logger.info("switch off device with id {}",device.getId());
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.switchOn(device);
    }
}
