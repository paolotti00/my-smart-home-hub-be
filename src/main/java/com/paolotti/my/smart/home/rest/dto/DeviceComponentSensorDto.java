package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceSensorTypeEnum;

public class DeviceComponentSensorDto extends DeviceComponentBaseDto {
    private DeviceSensorTypeEnum type;


    public DeviceSensorTypeEnum getType() {
        return type;
    }

    public void setType(DeviceSensorTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DeviceComponentSensorDto{" +
                "type=" + type +
                '}';
    }
}
