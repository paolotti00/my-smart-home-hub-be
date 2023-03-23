package com.paolotti.my.smart.home.dto.deprecated.mqtt;

public class PingDeviceStatusDto extends AckBaseDto {
    private DeviceStatusDto deviceStatus;

    public DeviceStatusDto getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatusDto deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
