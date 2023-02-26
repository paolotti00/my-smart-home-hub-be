package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.*;
import com.paolotti.my.smart.home.model.ColorRgb;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ToString
@Document(collection = "devices")
public class DeviceEntity extends BaseEntity {
    private String thingId;
    private UserEntity user;
    private NetworkData networkData;
    private String name;
    private ArrayList<DeviceComponent> components;
    private DeviceOperatingStatusEnum status;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

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

    public ArrayList<DeviceComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponent> components) {
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
    public abstract static class DeviceComponent {
        private String id;
        private DeviceComponentTypeEnum type;
        private DeviceOperatingStatusEnum status;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public DeviceComponentTypeEnum getType() {
            return type;
        }

        public void setType(DeviceComponentTypeEnum type) {
            this.type = type;
        }

        public DeviceOperatingStatusEnum getStatus() {
            return status;
        }

        public void setStatus(DeviceOperatingStatusEnum status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
    @ToString
    @Document(collection = "devices")
    public static class DeviceComponentLight extends DeviceComponent{
        private ColorRgb colorRgb;
        private int intensity;

        public ColorRgb getColorRgb() {
            return colorRgb;
        }

        public void setColorRgb(ColorRgb colorRgb) {
            this.colorRgb = colorRgb;
        }

        public int getIntensity() {
            return intensity;
        }

        public void setIntensity(int intensity) {
            this.intensity = intensity;
        }
    }
    @ToString
    @Document(collection = "devices")
    public static class DeviceComponentSensorTemperature extends DeviceComponent {
        private String temp;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
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
