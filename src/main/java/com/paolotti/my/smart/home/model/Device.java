package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.*;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
public class Device extends BaseModel{

    private String thingId;
    private List<String> usersOwnersIds;
    private NetworkData networkData;
    private String name;
    private ArrayList<DeviceComponent> components;
    private DeviceConnectionStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;
    private List<Action> supportedActions;

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

    public ArrayList<DeviceComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponent> components) {
        this.components = components;
    }

    public DeviceConnectionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceConnectionStatusEnum status) {
        this.status = status;
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

    public List<Action> getSupportedActions() {
        return supportedActions;
    }

    public void setSupportedActions(List<Action> supportedActions) {
        this.supportedActions = supportedActions;
    }
}
