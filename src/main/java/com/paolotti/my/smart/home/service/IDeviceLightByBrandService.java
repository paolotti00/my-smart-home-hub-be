package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;

public interface IDeviceLightByBrandService {
    public  <T extends Device> void switchOn(T device);
    public void switchOff(Device device);
    public void setColor(Device device, ColorRgb colorRgb);
}
