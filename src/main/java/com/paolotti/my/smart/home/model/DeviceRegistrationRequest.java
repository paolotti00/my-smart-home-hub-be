package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

public class DeviceRegistrationRequest {
    private String userId;
    private String deviceName;
    private String deviceMacAddress;
    private DeviceTypeEnum deviceType;
    private String deviceFirmwareVersion;
    // sensors detail
    private Integer numOfHumiditySensors;
    private Integer numOfLight;
    private Integer numOfHeatSensors;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceMacAddress() {
        return deviceMacAddress;
    }

    public void setDeviceMacAddress(String deviceMacAddress) {
        this.deviceMacAddress = deviceMacAddress;
    }

    public DeviceTypeEnum getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypeEnum deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceFirmwareVersion() {
        return deviceFirmwareVersion;
    }

    public void setDeviceFirmwareVersion(String deviceFirmwareVersion) {
        this.deviceFirmwareVersion = deviceFirmwareVersion;
    }

    public Integer getNumOfHumiditySensors() {
        return numOfHumiditySensors;
    }

    public void setNumOfHumiditySensors(Integer numOfHumiditySensors) {
        this.numOfHumiditySensors = numOfHumiditySensors;
    }

    public Integer getNumOfLight() {
        return numOfLight;
    }

    public void setNumOfLight(Integer numOfLight) {
        this.numOfLight = numOfLight;
    }

    public Integer getNumOfHeatSensors() {
        return numOfHeatSensors;
    }

    public void setNumOfHeatSensors(Integer numOfHeatSensors) {
        this.numOfHeatSensors = numOfHeatSensors;
    }
}
