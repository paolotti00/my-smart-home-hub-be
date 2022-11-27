package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;

public class DeviceComponentBase {
    private String id;
    private String name;
    private OnOffStatusEnum workingStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OnOffStatusEnum getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(OnOffStatusEnum workingStatus) {
        this.workingStatus = workingStatus;
    }
}
