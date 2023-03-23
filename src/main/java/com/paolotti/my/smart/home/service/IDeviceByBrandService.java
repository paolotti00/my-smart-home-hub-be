package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Action;

public interface IDeviceByBrandService {
    public  <T extends Device> void switchLight(T device, OnOffStatusEnum onOffStatusEnum) throws GenericException;
    public void setLightColor(Device device, String rgbColor) throws  GenericException;

    void doLightEffect(Device device, Action action) throws GenericException;
}
