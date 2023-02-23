package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceActionSchemaTypeEnum;
import lombok.ToString;

@ToString
public class DeviceActionsSchema {
    // the action schema is a set of action that the device can do
    // for example we can send to device the schema "blink" and if is supported the device has , defined in the fw, the steps that it have to do the action
    private String name;
    private String description;
    private DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeviceActionSchemaTypeEnum getDeviceActionSchemaTypeEnum() {
        return deviceActionSchemaTypeEnum;
    }

    public void setDeviceActionSchemaTypeEnum(DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum) {
        this.deviceActionSchemaTypeEnum = deviceActionSchemaTypeEnum;
    }

    public DeviceActionSchemaTypeEnum getDeviceComponentActionSchemaTypeEnum() {
        return deviceActionSchemaTypeEnum;
    }

    public void setDeviceComponentActionSchemaTypeEnum(DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum) {
        this.deviceActionSchemaTypeEnum = deviceActionSchemaTypeEnum;
    }
}
