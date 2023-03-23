package com.paolotti.my.smart.home.dto.deprecated.mqtt;

import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.deprecated.DeviceComponentDto;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
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
