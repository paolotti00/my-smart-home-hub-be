package com.paolotti.my.smart.home.dto.mqtt;

import com.paolotti.my.smart.home.dto.rest.SensorDto;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class DeviceStatusDto {
    private List<SensorDto> sensors;
    private Map<Integer, int[]> leds;

    public List<SensorDto> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorDto> sensors) {
        this.sensors = sensors;
    }

    public Map<Integer, int[]> getLeds() {
        return leds;
    }

    public void setLeds(Map<Integer, int[]> leds) {
        this.leds = leds;
    }
}
