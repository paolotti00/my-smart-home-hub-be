package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import com.paolotti.my.smart.home.rest.dto.reqres.BaseResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DeviceDto {
    private String id;
    private UserDto user;
    private NetworkDataDto networkData;
    private String name;
    private DeviceTypeEnum type;
    private ArrayList<DeviceComponentSensorDto> sensorList;
    private ArrayList<DeviceComponentLightDto> lightList;
    private ArrayList<String> groups;
    private DeviceOperatingStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime creationDate;
    private LocalDateTime activationDate;
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


    @Override
    public String toString() {
        return "DeviceDto{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", networkData=" + networkData +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", sensorList=" + sensorList +
                ", lightList=" + lightList +
                ", groups=" + groups +
                ", status=" + status +
                ", installationStatus=" + installationStatus +
                ", registrationDate=" + registrationDate +
                ", creationDate=" + creationDate +
                ", activationDate=" + activationDate +
                ", deviceBrandEnum=" + brand +
                '}';
    }
}
