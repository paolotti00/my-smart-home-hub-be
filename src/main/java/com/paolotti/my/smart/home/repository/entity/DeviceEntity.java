package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.*;
import com.paolotti.my.smart.home.model.ColorRgb;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ToString
@Document(collection = "devices")
public class DeviceEntity extends EntityBase {
    private UserEntity userOwner;
    private String name;
    private DeviceTypeEnum type;
    private NetworkData networkData;
    private ArrayList<DeviceComponentSensor> sensorList;
    private ArrayList<DeviceComponentLight> lightList;
    private Map<Integer, DeviceComponentTypeEnum> numberOfComponents;
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

    public Map<Integer, DeviceComponentTypeEnum> getNumberOfComponents() {
        return numberOfComponents;
    }

    public void setNumberOfComponents(Map<Integer, DeviceComponentTypeEnum> numberOfComponents) {
        this.numberOfComponents = numberOfComponents;
    }



    public static class DeviceComponentBase {
        private String id;
        private String name;
        private OnOffStatusEnum workingStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OnOffStatusEnum getWorkingStatus() {
            return workingStatus;
        }

        public void setWorkingStatus(OnOffStatusEnum workingStatus) {
            this.workingStatus = workingStatus;
        }
    }
    public static class DeviceComponentSensor extends DeviceComponentBase {
        private DeviceComponentTypeEnum type;

        public DeviceComponentTypeEnum getType() {
            return type;
        }

        public void setType(DeviceComponentTypeEnum type) {
            this.type = type;
        }
    }
    public static class DeviceComponentLight extends DeviceComponentBase {
        private int intensityPercentage; // 0 to 100
        private ColorRgb color;

        public int getIntensityPercentage() {
            return intensityPercentage;
        }

        public void setIntensityPercentage(int intensityPercentage) {
            this.intensityPercentage = intensityPercentage;
        }

        public ColorRgb getColor() {
            return color;
        }

        public void setColor(ColorRgb color) {
            this.color = color;
        }
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
