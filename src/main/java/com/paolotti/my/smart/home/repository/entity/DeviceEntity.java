package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.*;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ToString
@Document(collection = "devices")
public class DeviceEntity extends BaseEntity {
    private String thingId;
    private List<String> usersOwnersIds;
    private String roomId;
    private NetworkData networkData;
    private String name;
    private List<Sensor> sensors;
    private List<Map<Integer, int[]>> leds;
    private ConnectionStatusEnum connectionStatusEnum;
    private DeviceInstallationStatusEnum installationStatus;
    private LocalDateTime registrationDate;
    private LocalDateTime activationDate;
    private DeviceBrandEnum brand;
    private String firmwareVersion;
    private List<ExtraActionCommandData> supportedExtraActions;
    private List<ConnectionModeEnum> connectionMode;
    private List<ProtocolEnum> supportedProtocols;

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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
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

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Map<Integer, int[]>> getLeds() {
        return leds;
    }

    public void setLeds(List<Map<Integer, int[]>> leds) {
        this.leds = leds;
    }

    public ConnectionStatusEnum getConnectionStatusEnum() {
        return connectionStatusEnum;
    }

    public void setConnectionStatusEnum(ConnectionStatusEnum connectionStatusEnum) {
        this.connectionStatusEnum = connectionStatusEnum;
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

    public List<ExtraActionCommandData> getSupportedExtraActions() {
        return supportedExtraActions;
    }

    public void setSupportedExtraActions(List<ExtraActionCommandData> supportedExtraActions) {
        this.supportedExtraActions = supportedExtraActions;
    }

    public List<ConnectionModeEnum> getConnectionMode() {
        return connectionMode;
    }

    public void setConnectionMode(List<ConnectionModeEnum> connectionMode) {
        this.connectionMode = connectionMode;
    }

    public List<ProtocolEnum> getSupportedProtocols() {
        return supportedProtocols;
    }

    public void setSupportedProtocols(List<ProtocolEnum> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }

    @ToString
    public static class Sensor {
        private String id;
        private SensorTypeEnum type;
        private double value;
        private String unit;
        private LocalDateTime timestamp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public SensorTypeEnum getType() {
            return type;
        }

        public void setType(SensorTypeEnum type) {
            this.type = type;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class ExtraActionCommandData {
        private String name;
        private List<Field> fields;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Field> getFields() {
            return fields;
        }

        public void setFields(List<Field> fields) {
            this.fields = fields;
        }

        @ToString
        public static class Field {
            private String name;
            private FieldTypeEnum type;
            private boolean mandatory;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public FieldTypeEnum getType() {
                return type;
            }

            public void setType(FieldTypeEnum type) {
                this.type = type;
            }

            public boolean isMandatory() {
                return mandatory;
            }

            public void setMandatory(boolean mandatory) {
                this.mandatory = mandatory;
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
