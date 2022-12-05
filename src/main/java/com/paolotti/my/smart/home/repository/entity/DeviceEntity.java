package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.model.*;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

@Document(collection = "devices")
public class DeviceEntity extends EntityBase {
    private UserEntity user;
    private NetworkData networkData;
    private String name;
    private DeviceComponentWrapper components;
    private DeviceOperatingStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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

    public DeviceComponentWrapper getComponents() {
        return components;
    }

    public void setComponents(DeviceComponentWrapper components) {
        this.components = components;
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

    @ToString
    public static class DeviceComponentWrapper {
        private ArrayList<Map<Integer, DeviceComponentTypeEnum>> numberOfComponents;
        private ArrayList<DeviceComponent> componentsList;

        public ArrayList<Map<Integer, DeviceComponentTypeEnum>> getNumberOfComponents() {
            return numberOfComponents;
        }

        public void setNumberOfComponents(ArrayList<Map<Integer, DeviceComponentTypeEnum>> numberOfComponents) {
            this.numberOfComponents = numberOfComponents;
        }

        public ArrayList<DeviceComponent> getComponentsList() {
            return componentsList;
        }

        public void setComponentsList(ArrayList<DeviceComponent> componentsList) {
            this.componentsList = componentsList;
        }
    }
    @ToString
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
