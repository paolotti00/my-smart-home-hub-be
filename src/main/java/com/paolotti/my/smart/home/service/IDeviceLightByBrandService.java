package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;

public interface IDeviceLightByBrandService {
    public void switchOn(Device device);
    public void switchOff(Device device);
    public void setColor(Device device, ColorRgb colorRgb);
}
