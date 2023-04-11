package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Document(collection = "measurements")
public class MeasurementEntity extends BaseEntity {
    private String roomId;
    private String deviceId;
    private LocalDateTime date;
    private double value;
    private String unit;
    private MeasurementTypeEnum type;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public MeasurementTypeEnum getType() {
        return type;
    }

    public void setType(MeasurementTypeEnum type) {
        this.type = type;
    }
}
