package com.paolotti.my.smart.home.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paolotti.my.smart.home.enums.*;
import com.paolotti.my.smart.home.model.EffectData;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ToString
@Document(collection = "devices")
public class DeviceEntity extends BaseEntity {
    private String thingId;
    private List<String> usersOwnersIds;
    private NetworkData networkData;
    private String name;
    private ArrayList<DeviceComponentEntity> components;
    private DeviceConnectionStatusEnum status;
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

    public List<String> getUsersOwnersIds() {
        return usersOwnersIds;
    }

    public void setUsersOwnersIds(List<String> usersOwnersIds) {
        this.usersOwnersIds = usersOwnersIds;
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

    public ArrayList<DeviceComponentEntity> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<DeviceComponentEntity> components) {
        this.components = components;
    }

    public DeviceConnectionStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceConnectionStatusEnum status) {
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
    public abstract static class DeviceComponentEntity {
        private String id;
        private DeviceComponentTypeEnum type;
        private DeviceConnectionStatusEnum status;
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

        public DeviceConnectionStatusEnum getStatus() {
            return status;
        }

        public void setStatus(DeviceConnectionStatusEnum status) {
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
    public static class DeviceComponentEntityLight extends DeviceComponentEntity {
        private Map<Integer, LedEntity> leds;
        private ActionEntity action;

        public Map<Integer, LedEntity> getLeds() {
            return leds;
        }
        public void setLeds(Map<Integer, LedEntity> leds) {
            this.leds = leds;
        }

        public ActionEntity getAction() {
            return action;
        }

        public void setAction(ActionEntity action) {
            this.action = action;
        }

        public static class ActionEntity {
            private String name;
            private EffectDataEntity effectData;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public EffectDataEntity getEffectData() {
                return effectData;
            }

            public void setEffectData(EffectDataEntity effectData) {
                this.effectData = effectData;
            }
        }
        public static class EffectDataEntity{
            private String wait;
            private ArrayList<String> rgbColors;

            public String getWait() {
                return wait;
            }

            public void setWait(String wait) {
                this.wait = wait;
            }

            public ArrayList<String> getRgbColors() {
                return rgbColors;
            }

            public void setRgbColors(ArrayList<String> rgbColors) {
                this.rgbColors = rgbColors;
            }
        }
    }

    @ToString
    @Document(collection = "devices")
    public static class DeviceComponentEntitySensorTemperature extends DeviceComponentEntity {
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

    @ToString
    public static class LedEntity {
        String rgbColor;

        public String getRgbColor() {
            return rgbColor;
        }

        public void setRgbColor(String rgbColor) {
            this.rgbColor = rgbColor;
        }
    }
}
