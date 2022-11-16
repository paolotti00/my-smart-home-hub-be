package com.paolotti.my.smart.home.model;

public class DeviceComponentActionsSchema {
    // the action schema is a set of action that the device can do
    // for example we can send to device the schema "blink" and if is supported the device has , defined in the fw, the steps that it have to do the action
    private String name;
    private DeviceComponentActionsSchemaDetail detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceComponentActionsSchemaDetail getDetail() {
        return detail;
    }

    public void setDetail(DeviceComponentActionsSchemaDetail detail) {
        this.detail = detail;
    }
}
