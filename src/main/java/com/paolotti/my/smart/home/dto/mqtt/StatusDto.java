package com.paolotti.my.smart.home.dto.mqtt;

import com.paolotti.my.smart.home.dto.DeviceComponentDto;

import java.util.ArrayList;

public class StatusDto {
    private ArrayList<DeviceComponentDto> components;

    public ArrayList<DeviceComponentDto> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponentDto> components) {
        this.components = components;
    }
}
