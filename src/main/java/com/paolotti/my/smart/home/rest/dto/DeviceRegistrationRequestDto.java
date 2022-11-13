package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

public class DeviceRegistrationRequestDto {
    private String userId;
    private String deviceName;
    private NetworkData networkData;
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

    public NetworkData getNetworkData() {
        return networkData;
    }

    public void setNetworkData(NetworkData networkData) {
        this.networkData = networkData;
    }

    public static class NetworkData {
        String ip;
        String macAddress;
        String name;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
