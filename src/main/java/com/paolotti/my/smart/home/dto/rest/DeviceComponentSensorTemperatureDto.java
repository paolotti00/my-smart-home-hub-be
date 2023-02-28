package com.paolotti.my.smart.home.dto.rest;

import com.paolotti.my.smart.home.dto.DeviceComponentDto;

public class DeviceComponentSensorTemperatureDto extends DeviceComponentDto {
    private String temp;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
