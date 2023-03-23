package com.paolotti.my.smart.home.model.deprecated;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class DeviceStatus {
    private ArrayList<DeviceComponent> components;

    public ArrayList<DeviceComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponent> components) {
        this.components = components;
    }
}
