package com.paolotti.my.smart.home.mqtt.dto;

import lombok.ToString;

@ToString
public class LightDto {
    private ComponentLedDto led;
    private ActionDto runningAction;

    public ComponentLedDto getLed() {
        return led;
    }

    public void setLed(ComponentLedDto led) {
        this.led = led;
    }

    public ActionDto getRunningAction() {
        return runningAction;
    }

    public void setRunningAction(ActionDto runningAction) {
        this.runningAction = runningAction;
    }
}
