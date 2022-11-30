package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import lombok.ToString;

@ToString
public class DeviceComponentSensorDto extends DeviceComponentBaseDto {
    private DeviceComponentTypeEnum type;


    public DeviceComponentTypeEnum getType() {
        return type;
    }

    public void setType(DeviceComponentTypeEnum type) {
        this.type = type;
    }

}
