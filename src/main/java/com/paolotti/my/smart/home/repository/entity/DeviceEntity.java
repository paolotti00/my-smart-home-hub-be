package com.paolotti.my.smart.home.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "devices")
public class DeviceEntity {
    @Id
    private String id;
    private UserEntity user;
    private String macAddress;
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

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public ArrayList<String> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<String> groups) {
        this.groups = groups;
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

    public enum OnOffStatusEnum{
        ON,
        OFF
    }
    public enum DeviceOperatingStatusEnum{
        ONLINE, // the device was discovered and activated and is online
        OFFLINE, // the device was discovered and activated and is offline

    }
    public enum DeviceInstallationStatusEnum{
        TO_ACTIVATE, // the device was discovered but not activated yet
        ACTIVE, // the device was discovered and activated
        DEACTIVATED, // the device was discovered and activated then was deactivated
        BLACKLISTED, // the device was discovered and blacklisted (so it will not discover anymore)
    }
    public enum DeviceTypeEnum{
        SENSOR,
        LIGHT,
        MIX
    }
    public enum DeviceSensorTypeEnum{
        HEAT,
        HUMIDITY,
        MIX
    }

    public static class DeviceElementBase{
        private String id;
        private OnOffStatusEnum workingStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public OnOffStatusEnum getWorkingStatus() {
            return workingStatus;
        }

        public void setWorkingStatus(OnOffStatusEnum workingStatus) {
            this.workingStatus = workingStatus;
        }
    }
    public static class DeviceElementSensor extends DeviceElementBase{
        private DeviceSensorTypeEnum type;

        public DeviceSensorTypeEnum getType() {
            return type;
        }

        public void setType(DeviceSensorTypeEnum type) {
            this.type = type;
        }
    }
    public static class DeviceElementLight extends DeviceElementBase{

    }

}
