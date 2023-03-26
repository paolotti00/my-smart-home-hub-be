package com.paolotti.my.smart.home.model;

import lombok.ToString;

@ToString
public class PingDeviceStatus extends AckBase{
    private DeviceStatus deviceStatus;

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
