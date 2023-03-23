package com.paolotti.my.smart.home.model.deprecated;

import lombok.ToString;

import java.awt.*;
import java.util.ArrayList;

@ToString
@Deprecated
public class EffectData {
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

    public EffectData() {
    }

    public EffectData(String wait, ArrayList<String> rgbColors) {
        this.wait = wait;
        this.rgbColors = rgbColors;
    }
}
