package com.paolotti.my.smart.home.model.deprecated;

import com.paolotti.my.smart.home.model.Action;

import java.util.Map;
@Deprecated
public class DeviceComponentLight extends DeviceComponent{
    private Map<Integer,Led> leds;
    private Action action;

    public Map<Integer, Led> getLeds() {
        return leds;
    }

    public void setLeds(Map<Integer, Led> leds) {
        this.leds = leds;
    }

    public void setLed(int position, Led led) {
        this.leds.put(position, led);
    }

    public Led getLed(int position) {
        return this.leds.get(position);
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
