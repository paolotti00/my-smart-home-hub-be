package com.paolotti.my.smart.home.dto;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class MeasurementDto {
    private LocalDateTime date;
    private double value;
    private String unit;
    private MeasurementTypeEnum type;

    private String deviceId;

    // used for receive measurement by device on mqtt
    private String thingId;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }
}
