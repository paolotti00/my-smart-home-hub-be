package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.DeviceComponent;

public class DeviceComponentLightDto extends DeviceComponentDto {
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
