package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.service.IDeviceByBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeviceByBrandYeelightServiceImpl implements IDeviceByBrandService {
    private static final Logger logger = LoggerFactory.getLogger(DeviceByBrandYeelightServiceImpl.class);
    @Override
    public void switchOn(Device device) {
        logger.info("device with id {} and brand yeelight is ON!!",device.getId());

    }

    @Override
    public void switchOff(Device device) {
        logger.info("device with id {} and brand yeelight is OFF!!",device.getId());

    }

    @Override
    public void setColor(Device device, ColorRgb colorRgb) {
        logger.info("device with id {} and brand yeelight has been set to {} color!!",device.getId(),colorRgb);
    }
}
