package com.paolotti.my.smart.home.model;

public class DeviceComponentLight extends DeviceComponent{
    private ColorRgb colorRgb;
    private int intensity;


    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public ColorRgb getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(ColorRgb colorRgb) {
        this.colorRgb = colorRgb;
    }

}
