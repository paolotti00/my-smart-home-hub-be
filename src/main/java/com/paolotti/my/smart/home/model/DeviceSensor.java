package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceSensorTypeEnum;

public class DeviceSensor extends DeviceBaseElement{
    private DeviceSensorTypeEnum type;


    public DeviceSensorTypeEnum getType() {
        return type;
    }

    public void setType(DeviceSensorTypeEnum type) {
        this.type = type;
    }
}
