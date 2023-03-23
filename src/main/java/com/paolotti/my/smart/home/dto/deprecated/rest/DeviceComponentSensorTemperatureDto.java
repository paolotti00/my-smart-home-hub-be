package com.paolotti.my.smart.home.dto.deprecated.rest;

import com.paolotti.my.smart.home.dto.deprecated.DeviceComponentDto;

public class DeviceComponentSensorTemperatureDto extends DeviceComponentDto {
    private Double temp;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
