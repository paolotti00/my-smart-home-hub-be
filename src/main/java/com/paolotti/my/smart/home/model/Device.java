package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Device {
    private String id;
    private User user;
    private NetworkData networkData;
    private String name;
    private DeviceTypeEnum type;
    private ArrayList<DeviceComponentSensor> sensorList;
    private ArrayList<DeviceComponentLight> lightList;
    private ArrayList<String> groups;
    private DeviceOperatingStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime creationDate;
    private LocalDateTime activationDate;

    private ArrayList<DeviceActionsSchema> supportedSchemas;
    private DeviceBrandEnum brand;

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<DeviceComponentSensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<DeviceComponentSensor> sensorList) {
        this.sensorList = sensorList;
    }

    public ArrayList<DeviceComponentLight> getLightList() {
        return lightList;
    }

    public void setLightList(ArrayList<DeviceComponentLight> lightList) {
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

    public ArrayList<DeviceActionsSchema> getSupportedSchemas() {
        return supportedSchemas;
    }

    public void setSupportedSchemas(ArrayList<DeviceActionsSchema> supportedSchemas) {
        this.supportedSchemas = supportedSchemas;
    }

    public DeviceBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(DeviceBrandEnum brand) {
        this.brand = brand;
    }
}
