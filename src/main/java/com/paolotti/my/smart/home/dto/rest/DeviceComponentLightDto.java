package com.paolotti.my.smart.home.dto.rest;

import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.DeviceComponentDto;

import java.util.Map;

public class DeviceComponentLightDto extends DeviceComponentDto {
    private Map<Integer, LedDto> leds;
    private ActionDto action;

    public Map<Integer, LedDto> getLeds() {
        return leds;
    }

    public void setLeds(Map<Integer, LedDto> leds) {
        this.leds = leds;
    }

    public ActionDto getAction() {
        return action;
    }

    public void setAction(ActionDto action) {
        this.action = action;
    }
}
