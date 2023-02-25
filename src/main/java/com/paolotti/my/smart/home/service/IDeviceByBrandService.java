package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.LightEffectMessage;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface IDeviceByBrandService {
    public  <T extends Device> void switchOn(T device);
    public void switchOff(Device device);
    public void setColor(Device device, ColorRgb colorRgb) throws  GenericException;

    void doEffect(Device device, LightEffectMessage lightEffectMessage) throws GenericException;
}
