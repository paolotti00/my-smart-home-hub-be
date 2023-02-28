package com.paolotti.my.smart.home.dto;

import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;

@ToString
public class EffectDataDto {
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

    public EffectDataDto() {
    }

    public EffectDataDto(String wait, ArrayList<String> rgbColors) {
        this.wait = wait;
        this.rgbColors = rgbColors;
    }
}
