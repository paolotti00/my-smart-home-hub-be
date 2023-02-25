package com.paolotti.my.smart.home.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.paolotti.my.smart.home.rest.dto.mqtt.EffectDataDto;
import lombok.ToString;

@ToString
public class LightEffectMessage {
    private String name;
    @JsonProperty("effect_data")
    private EffectDataDto effectData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EffectDataDto getEffectData() {
        return effectData;
    }

    public void setEffectData(EffectDataDto effectData) {
        this.effectData = effectData;
    }

    public LightEffectMessage() {
    }

    public LightEffectMessage(String name, EffectDataDto effectData) {
        this.name = name;
        this.effectData = effectData;
    }
}
