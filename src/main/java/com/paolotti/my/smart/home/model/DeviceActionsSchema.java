package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceActionSchemaTypeEnum;
import lombok.ToString;

@ToString
public class DeviceActionsSchema {
    // the action schema is a set of action that the device can do
    // for example we can send to device the schema "blink" and if is supported the device has , defined in the fw, the steps that it have to do the action
    private String name;
    private ColorRgb colorRgb;
    private Long durationMs;
    private int repetition;
    private DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorRgb getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(ColorRgb colorRgb) {
        this.colorRgb = colorRgb;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public DeviceActionSchemaTypeEnum getDeviceComponentActionSchemaTypeEnum() {
        return deviceActionSchemaTypeEnum;
    }

    public void setDeviceComponentActionSchemaTypeEnum(DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum) {
        this.deviceActionSchemaTypeEnum = deviceActionSchemaTypeEnum;
    }
}
