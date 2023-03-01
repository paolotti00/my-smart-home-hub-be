package com.paolotti.my.smart.home.model;

import java.util.ArrayList;

public class DeviceStatus {
    private ArrayList<DeviceComponent> components;

    public ArrayList<DeviceComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponent> components) {
        this.components = components;
    }
}
