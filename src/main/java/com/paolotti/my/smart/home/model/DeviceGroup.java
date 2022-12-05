package com.paolotti.my.smart.home.model;

import java.util.List;

public class DeviceGroup extends BaseGroup {
    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
