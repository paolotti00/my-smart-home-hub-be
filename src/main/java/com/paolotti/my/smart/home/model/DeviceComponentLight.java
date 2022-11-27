package com.paolotti.my.smart.home.model;

import lombok.ToString;

@ToString
public class DeviceComponentLight extends DeviceComponentBase {
    private int intensityPercentage; // 0 to 100
    private ColorRgb color;

    public int getIntensityPercentage() {
        return intensityPercentage;
    }

    public void setIntensityPercentage(int intensityPercentage) {
        this.intensityPercentage = intensityPercentage;
    }

    public ColorRgb getColor() {
        return color;
    }

    public void setColor(ColorRgb color) {
        this.color = color;
    }
}
