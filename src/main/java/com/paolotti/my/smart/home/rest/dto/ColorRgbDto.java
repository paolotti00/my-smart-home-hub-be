package com.paolotti.my.smart.home.rest.dto;

import lombok.ToString;

@ToString
public class ColorRgbDto {
    int r;
    int g;
    int b;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
