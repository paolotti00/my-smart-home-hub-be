package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Device {
    private String id;
    private User user;
    private String macAddress;
    private String name;
    DeviceTypeEnum type;
    ArrayList<DeviceSensor> sensorList;
    ArrayList<DeviceLight> lightList;
    ArrayList<String> groups;
    DeviceStatusEnum status;
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

    public ArrayList<DeviceSensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(ArrayList<DeviceSensor> sensorList) {
        this.sensorList = sensorList;
    }

    public ArrayList<DeviceLight> getLightList() {
        return lightList;
    }

    public void setLightList(ArrayList<DeviceLight> lightList) {
        this.lightList = lightList;
    }

    public DeviceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceStatusEnum status) {
        this.status = status;
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
