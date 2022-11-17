package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.service.IDeviceLightByBrandService;
import org.springframework.stereotype.Service;

@Service
public class DeviceLightByBrandYeelightServiceImpl implements IDeviceLightByBrandService {
    public <T extends Device> void switchOn (T device) {


    }

    @Override
    public void switchOff(Device device) {

    }

    @Override
    public void setColor(Device device, ColorRgb colorRgb) {

    }
}
