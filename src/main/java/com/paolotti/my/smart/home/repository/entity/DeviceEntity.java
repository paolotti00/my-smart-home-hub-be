package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Document(collection = "devices")
public class DeviceEntity extends EntityBase {
    private UserEntity userOwner;
    private String name;
    private DeviceTypeEnum type;
    private NetworkData networkData;
    private ArrayList<DeviceComponentSensor> sensorList;
    private ArrayList<DeviceComponentLight> lightList;
    @DocumentReference(lazy=true)
    private List<GroupDeviceEntity> deviceGroups;
    private DeviceOperatingStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private DeviceBrandEnum brand;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;

    public UserEntity getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserEntity userOwner) {
        this.userOwner = userOwner;
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

    public List<GroupDeviceEntity> getDeviceGroups() {
        return deviceGroups;
    }

    public void setDeviceGroups(List<GroupDeviceEntity> deviceGroups) {
        this.deviceGroups = deviceGroups;
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(LocalDateTime activationDate) {
        this.activationDate = activationDate;
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

    public DeviceBrandEnum getBrand() {
        return brand;
    }

    public void setBrand(DeviceBrandEnum brand) {
        this.brand = brand;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public NetworkData getNetworkData() {
        return networkData;
    }

    public void setNetworkData(NetworkData networkData) {
        this.networkData = networkData;
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

    public static class DeviceComponentBase {
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
    public static class DeviceComponentSensor extends DeviceComponentBase {
        private DeviceSensorTypeEnum type;

        public DeviceSensorTypeEnum getType() {
            return type;
        }

        public void setType(DeviceSensorTypeEnum type) {
            this.type = type;
        }
    }
    public static class DeviceComponentLight extends DeviceComponentBase {

    }

    public static class NetworkData {
        String ip;
        String macAddress;
        String name;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
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
    }



}
