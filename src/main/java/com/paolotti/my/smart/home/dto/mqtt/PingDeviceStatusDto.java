package com.paolotti.my.smart.home.dto.mqtt;

import lombok.ToString;

@ToString
public class PingDeviceStatusDto extends AckBaseDto {
    private DeviceStatusDto deviceStatus;

    public DeviceStatusDto getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatusDto deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
