package com.paolotti.my.smart.home.utility;

import java.awt.*;

public  class  Utility {
    public static String colorToString(Color color){
        return color.getRed()+","+color.getGreen()+","+color.getBlue();
    }
    public static Color parseColor(String colorString) {
        String[] parts = colorString.split("[,\\s]+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid color string: " + colorString);
        }
        int r = Integer.parseInt(parts[0]);
        int g = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        return new Color(r, g, b);
    }
}
