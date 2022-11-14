package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

public class DeviceRegistrationRequestDto {
    private String deviceName;
    private NetworkDataDto networkData;
    private DeviceTypeEnum deviceType;
    private String deviceFirmwareVersion;
    // sensors detail
    private Integer numOfHumiditySensors;
    private Integer numOfLight;
    private Integer numOfHeatSensors;
    private String userEmail;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public NetworkDataDto getNetworkData() {
        return networkData;
    }

    public void setNetworkData(NetworkDataDto networkData) {
        this.networkData = networkData;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "DeviceRegistrationRequestDto{" +
                "deviceName='" + deviceName + '\'' +
                ", networkData=" + networkData +
                ", deviceType=" + deviceType +
                ", deviceFirmwareVersion='" + deviceFirmwareVersion + '\'' +
                ", numOfHumiditySensors=" + numOfHumiditySensors +
                ", numOfLight=" + numOfLight +
                ", numOfHeatSensors=" + numOfHeatSensors +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
