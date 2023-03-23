package com.paolotti.my.smart.home.model.deprecated;

import com.paolotti.my.smart.home.model.Device;

import java.util.List;

@Deprecated
public class DeviceGroup extends BaseGroup {
    private List<Device> devices;

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
