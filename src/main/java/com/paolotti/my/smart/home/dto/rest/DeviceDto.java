package com.paolotti.my.smart.home.dto.rest;

import com.paolotti.my.smart.home.dto.ExtraActionCommandDataDto;
import com.paolotti.my.smart.home.enums.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ToString
public class DeviceDto {
    private String thingId;
    private List<String> usersOwnersIds;
    private String roomId;
    private NetworkDataDto networkData;
    private String name;
    private List<SensorDto> sensors;
    private ConnectionStatusEnum connectionStatus;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;
    private Map<Integer, int[]> leds;
    private List<ExtraActionCommandDataDto> supportedExtraActions;
    private List<ConnectionModeEnum> connectionMode;
    private List<ProtocolEnum> supportedProtocols;

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public List<String> getUsersOwnersIds() {
        return usersOwnersIds;
    }

    public void setUsersOwnersIds(List<String> usersOwnersIds) {
        this.usersOwnersIds = usersOwnersIds;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public NetworkDataDto getNetworkData() {
        return networkData;
    }

    public void setNetworkData(NetworkDataDto networkData) {
        this.networkData = networkData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SensorDto> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDto> sensors) {
        this.sensors = sensors;
    }

    public ConnectionStatusEnum getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ConnectionStatusEnum connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public DeviceInstallationStatusEnum getInstallationStatus() {
        return installationStatus;
    }

    public void setInstallationStatus(DeviceInstallationStatusEnum installationStatus) {
        this.installationStatus = installationStatus;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
    }

    public DeviceBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(DeviceBrandEnum brand) {
        this.brand = brand;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public Map<Integer, int[]> getLeds() {
        return leds;
    }

    public void setLeds(Map<Integer, int[]> leds) {
        this.leds = leds;
    }

    public List<ExtraActionCommandDataDto> getSupportedExtraActions() {
        return supportedExtraActions;
    }

    public void setSupportedExtraActions(List<ExtraActionCommandDataDto> supportedExtraActions) {
        this.supportedExtraActions = supportedExtraActions;
    }

    public List<ConnectionModeEnum> getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(List<ConnectionModeEnum> connectionMode) {
        this.connectionMode = connectionMode;
    }

    public List<ProtocolEnum> getSupportedProtocols() {
        return supportedProtocols;
    }

    public void setSupportedProtocols(List<ProtocolEnum> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }
}
