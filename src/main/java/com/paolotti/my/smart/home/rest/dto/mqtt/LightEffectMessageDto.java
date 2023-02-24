package com.paolotti.my.smart.home.rest.dto.mqtt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class LightEffectMessageDto {
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

    public LightEffectMessageDto() {
    }

    public LightEffectMessageDto(String name, EffectDataDto effectData) {
        this.name = name;
        this.effectData = effectData;
    }
}
