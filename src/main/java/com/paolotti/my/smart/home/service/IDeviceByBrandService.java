package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Action;

import java.awt.*;

public interface IDeviceByBrandService {
    public  <T extends Device> void switchOn(T device);
    public void switchOff(Device device);
    public void setColor(Device device, String rgbColor) throws  GenericException;

    void doEffect(Device device, Action action) throws GenericException;
}
