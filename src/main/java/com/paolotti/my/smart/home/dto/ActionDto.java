package com.paolotti.my.smart.home.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class ActionDto {
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

    public ActionDto() {
    }

    public ActionDto(String name, EffectDataDto effectData) {
        this.name = name;
        this.effectData = effectData;
    }
}
