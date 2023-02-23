package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.*;
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
        private Map< DeviceComponentTypeEnum,Integer> numberOfComponents;
        private ArrayList<DeviceComponent> componentsList;

        public Map< DeviceComponentTypeEnum,Integer> getNumberOfComponents() {
            return numberOfComponents;
        }

        public void setNumberOfComponents(Map< DeviceComponentTypeEnum,Integer> numberOfComponents) {
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
    public static class DeviceComponent {
        private String id;
        private DeviceComponentTypeEnum type;
        private DeviceComponentWorkingStatus workingStatus;
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

        public DeviceComponentWorkingStatus getWorkingStatus() {
            return workingStatus;
        }

        public void setWorkingStatus(DeviceComponentWorkingStatus workingStatus) {
            this.workingStatus = workingStatus;
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

        @ToString
        public static class DeviceComponentWorkingStatus{
            private DeviceWorkingStatusOut out;
            private DeviceWorkingStatusIn in;
            private OnOffStatusEnum powerStatus;



            public OnOffStatusEnum getPowerStatus() {
                return powerStatus;
            }

            public void setPowerStatus(OnOffStatusEnum powerStatus) {
                this.powerStatus = powerStatus;
            }
            @ToString
            public static class DeviceWorkingStatusOut {
                private int intensity;
                private OnOffStatusEnum powerStatus;
                private ColorRgb colorRgb;

                public int getIntensity() {
                    return intensity;
                }

                public void setIntensity(int intensity) {
                    this.intensity = intensity;
                }

                public OnOffStatusEnum getPowerStatus() {
                    return powerStatus;
                }

                public void setPowerStatus(OnOffStatusEnum powerStatus) {
                    this.powerStatus = powerStatus;
                }

                public ColorRgb getColorRgb() {
                    return colorRgb;
                }

                public void setColorRgb(ColorRgb colorRgb) {
                    this.colorRgb = colorRgb;
                }
            }
            @ToString
            public static class DeviceWorkingStatusIn {
                private double temperature;

                public double getTemperature() {
                    return temperature;
                }

                public void setTemperature(double temperature) {
                    this.temperature = temperature;
                }
            }
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
