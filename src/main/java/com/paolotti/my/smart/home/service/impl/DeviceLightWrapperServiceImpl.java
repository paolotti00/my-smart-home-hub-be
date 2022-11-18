package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.mapper.IDeviceMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.repository.IDeviceCustomRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.service.IDeviceLightByBrandService;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Device device = deviceService.retrieveDeviceById(deviceId);
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.switchOff(device);

    }

    @Override
    public void switchOnAllLightsByGroup(String userId, String groupId) {

    }

    @Override
    public void switchOffAllLightsByGroup(String userId, String groupId) {

    }
}
