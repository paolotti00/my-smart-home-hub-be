package com.paolotti.my.smart.home.dto.mqtt;

import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.DeviceComponentDto;

import java.util.ArrayList;
import java.util.List;

public class DeviceStatusDto {
    private ArrayList<DeviceComponentDto> components;
    private List<ActionDto> supportedActions;

    public ArrayList<DeviceComponentDto> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponentDto> components) {
        this.components = components;
    }

    public List<ActionDto> getSupportedActions() {
        return supportedActions;
    }

    public void setSupportedActions(List<ActionDto> supportedActions) {
        this.supportedActions = supportedActions;
    }
}
