package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Measurement extends BaseModel {
    private LocalDateTime date;
    private double value;
    private String unit;
    private MeasurementTypeEnum type;

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
