package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.SensorTypeEnum;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Sensor {
    private String id;
    private SensorTypeEnum type;
    private double value;
    private String unit;
    private LocalDateTime timestamp;

    public Sensor(String id, SensorTypeEnum type, double value, String unit, LocalDateTime timestamp) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.timestamp = timestamp;
    }

    public Sensor() {
    }
    // Getter and Setter methods

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
