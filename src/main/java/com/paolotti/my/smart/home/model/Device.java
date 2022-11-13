package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Device {
    private String id;
    private User user;
    private NetworkData networkData;
    private String name;
    DeviceTypeEnum type;
    ArrayList<DeviceElementSensor> sensorList;
    ArrayList<DeviceElementLight> lightList;
    ArrayList<String> groups;
    DeviceOperatingStatusEnum status;
    DeviceInstallationStatusEnum installationStatus;
    LocalDateTime registrationDate;
    LocalDateTime creationDate;

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

    public ArrayList<DeviceElementSensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<DeviceElementSensor> sensorList) {
        this.sensorList = sensorList;
    }

    public ArrayList<DeviceElementLight> getLightList() {
        return lightList;
    }

    public void setLightList(ArrayList<DeviceElementLight> lightList) {
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
}
