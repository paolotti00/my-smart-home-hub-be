package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;

public class DeviceBaseElement {
    private String id;
    private OnOffStatusEnum workingStatus;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public OnOffStatusEnum getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(OnOffStatusEnum workingStatus) {
        this.workingStatus = workingStatus;
    }
}
