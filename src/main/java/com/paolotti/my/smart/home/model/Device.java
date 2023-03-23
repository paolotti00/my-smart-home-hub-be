package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.ConnectionModeEnum;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.ProtocolEnum;
import com.paolotti.my.smart.home.enums.ConnectionStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ToString
public class Device extends BaseModel {

    private String thingId;
    private List<String> usersOwnersIds;
    private String roomId;
    private NetworkData networkData;
    private String name;
    private List<Sensor> sensors;
    private ConnectionStatusEnum connectionStatusEnum;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;
    private List<Map<Integer, int[]>> leds;
    private List<Action> supportedActions;
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

    public NetworkData getNetworkData() {
        return networkData;
    }

    public void setNetworkData(NetworkData networkData) {
        this.networkData = networkData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public ConnectionStatusEnum getConnectionStatusEnum() {
        return connectionStatusEnum;
    }

    public void setConnectionStatusEnum(ConnectionStatusEnum connectionStatusEnum) {
        this.connectionStatusEnum = connectionStatusEnum;
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

    public List<Map<Integer, int[]>> getLeds() {
        return leds;
    }

    public void setLeds(List<Map<Integer, int[]>> leds) {
        this.leds = leds;
    }

    public List<Action> getSupportedActions() {
        return supportedActions;
    }

    public void setSupportedActions(List<Action> supportedActions) {
        this.supportedActions = supportedActions;
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
