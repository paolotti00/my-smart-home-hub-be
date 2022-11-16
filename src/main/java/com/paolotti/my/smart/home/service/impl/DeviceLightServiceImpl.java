package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.factory.IBeanFactoryService;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.service.IDeviceLightByBrandService;
import com.paolotti.my.smart.home.service.IDeviceLightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceLightServiceImpl implements IDeviceLightService {
    @Autowired
    IBeanFactoryService beanFactoryService;
    @Override
    public void switchOnAllLightsByDevice(String userId, String deviceId) throws BrandNotSupportedException {
        // todo log
        // todo retrieve the user
        // check if have the permission to do something
        // todo retrieve the device
        Device device = null;
        IDeviceLightByBrandService deviceLightByBrandService = beanFactoryService.getDeviceLightByBrandServiceImpl(device.getBrand());
        deviceLightByBrandService.switchOn(device);


    }

    @Override
    public void switchOffAllLightsByDevice(String userId, String deviceId) {

    }

    @Override
    public void switchOnAllLightsByGroup(String userId, String groupId) {

    }

    @Override
    public void switchOffAllLightsByGroup(String userId, String groupId) {

    }
}
