package com.paolotti.my.smart.home.model;

import lombok.ToString;
import java.util.List;
import java.util.Map;

@ToString
public class DeviceStatus {
    private List<Sensor> sensors;
    private Map<Integer, int[]>leds;

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Map<Integer, int[]> getLeds() {
        return leds;
    }

    public void setLeds(Map<Integer, int[]> leds) {
        this.leds = leds;
    }
}
