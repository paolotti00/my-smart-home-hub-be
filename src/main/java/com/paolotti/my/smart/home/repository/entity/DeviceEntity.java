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
    private List<Action> supportedActions;
    private List<ConnectionModeEnum> connectionMode;
    private List<ProtocolEnum> supportedProtocols;

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

    public static class Action {
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
