package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceLightStatus;

public class DeviceLight extends Device{
    DeviceLightStatus lightStatus;

    public DeviceLightStatus getLightStatus() {
        return lightStatus;
    }

    public void setLightStatus(DeviceLightStatus lightStatus) {
        this.lightStatus = lightStatus;
    }
}
