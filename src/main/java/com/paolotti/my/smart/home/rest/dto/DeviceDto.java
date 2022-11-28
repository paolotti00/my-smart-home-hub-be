package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.config.JsonViewConfig;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ToString
public class DeviceDto {
    @JsonView(JsonViewConfig.AsOutput.class)
    private String id;
    @JsonView(JsonViewConfig.AsOutput.class)
    private UserDto user;
    @JsonView(JsonViewConfig.AsOutput.class)
    private NetworkDataDto networkData;
    @JsonView(JsonViewConfig.AsInput.class)
    private String name;
    @JsonView(JsonViewConfig.AsInput.class)
    private DeviceTypeEnum type;
    @JsonView(JsonViewConfig.AsOutput.class)
    private ArrayList<DeviceComponentSensorDto> sensorList;
    @JsonView(JsonViewConfig.AsInput.class)
    private ArrayList<DeviceComponentLightDto> lightList;
    @JsonView(JsonViewConfig.AsOutput.class)
    private ArrayList<String> groups;
    @JsonView(JsonViewConfig.AsOutput.class)
    private DeviceOperatingStatusEnum status;
    @JsonView(JsonViewConfig.AsOutput.class)
    private DeviceInstallationStatusEnum installationStatus;
    @JsonView(JsonViewConfig.AsOutput.class)
    private LocalDateTime registrationDate;
    @JsonView(JsonViewConfig.AsOutput.class)
    private LocalDateTime creationDate;
    @JsonView(JsonViewConfig.AsOutput.class)
    private LocalDateTime activationDate;
    @JsonView(JsonViewConfig.AsInput.class)
    private DeviceBrandEnum brand;

    public String getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
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

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
    }

    public ArrayList<DeviceComponentSensorDto> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<DeviceComponentSensorDto> sensorList) {
        this.sensorList = sensorList;
    }

    public ArrayList<DeviceComponentLightDto> getLightList() {
        return lightList;
    }

    public void setLightList(ArrayList<DeviceComponentLightDto> lightList) {
        this.lightList = lightList;
    }

    public DeviceOperatingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceOperatingStatusEnum status) {
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


}
