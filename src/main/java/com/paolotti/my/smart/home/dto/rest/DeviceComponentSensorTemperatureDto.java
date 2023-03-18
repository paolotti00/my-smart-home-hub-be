package com.paolotti.my.smart.home.dto.rest;

import com.paolotti.my.smart.home.dto.DeviceComponentDto;

public class DeviceComponentSensorTemperatureDto extends DeviceComponentDto {
    private Double temp;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
