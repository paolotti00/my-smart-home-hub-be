package com.paolotti.my.smart.home.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Action {
    private String name;
    @JsonProperty("effect_data")
    private EffectData effectData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EffectData getEffectData() {
        return effectData;
    }

    public void setEffectData(EffectData effectData) {
        this.effectData = effectData;
    }

    public Action() {
    }

    public Action(String name, EffectData effectData) {
        this.name = name;
        this.effectData = effectData;
    }
}
