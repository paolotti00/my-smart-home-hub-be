package com.paolotti.my.smart.home.model.deprecated;

import java.awt.*;

@Deprecated
public class Led {
    String rgbColor;

    public String getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

//    public Color getColor() {
//        return color;
//    }
//
//    public void setColor(String colorString) { // R,G,B
//        String[] parts = colorString.split("[,\\s]+");
//        if (parts.length != 3) {
//            throw new IllegalArgumentException("Invalid color string: " + colorString);
//        }
//        int r = Integer.parseInt(parts[0]);
//        int g = Integer.parseInt(parts[1]);
//        int b = Integer.parseInt(parts[2]);
//        this.color =  new Color(r, g, b);
//    }
}
