package com.paolotti.my.smart.home.dto.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.DeviceComponentDto;
import com.paolotti.my.smart.home.enums.*;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
public class DeviceDto {
    @JsonView(JsonViewConfig.HighDetail.class)
    private String id;
    @JsonView(JsonViewConfig.HighDetail.class)
    private String thingId;;
    @JsonView(JsonViewConfig.LowDetail.class)
    private List<String> usersOwnersIds;
    @JsonView(JsonViewConfig.LowDetail.class)
    private NetworkDataDto networkData;
    @JsonView(JsonViewConfig.LowDetail.class)
    private String name;
    @JsonView(JsonViewConfig.LowDetail.class)
    private ArrayList<DeviceComponentDto> components;
    @JsonView(JsonViewConfig.HighDetail.class)
    private DeviceConnectionStatusEnum status;
    @JsonView(JsonViewConfig.HighDetail.class)
    private DeviceInstallationStatusEnum installationStatus;
    @JsonView(JsonViewConfig.HighDetail.class)
    private LocalDateTime registrationDate;
    @JsonView(JsonViewConfig.HighDetail.class)
    private LocalDateTime creationDate;
    @JsonView(JsonViewConfig.HighDetail.class)
    private LocalDateTime activationDate;
    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceBrandEnum brand;
    @JsonView(JsonViewConfig.MediumDetail.class)
    private String firmwareVersion;
    @JsonView(JsonViewConfig.HighDetail.class)
    private List<ActionDto> supportedActions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public ArrayList<DeviceComponentDto> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponentDto> components) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
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

    public List<ActionDto> getSupportedActions() {
        return supportedActions;
    }

    public void setSupportedActions(List<ActionDto> supportedActions) {
        this.supportedActions = supportedActions;
    }
}
