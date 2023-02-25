package com.paolotti.my.smart.home.model;

import lombok.ToString;

import java.util.ArrayList;

@ToString
public class EffectData {
    private String wait;
    private ArrayList<String> colors;

    public String getWait() {
        return wait;
    }

    public void setWait(String wait) {
        this.wait = wait;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public EffectData() {
    }

    public EffectData(String wait, ArrayList<String> colors) {
        this.wait = wait;
        this.colors = colors;
    }
}
