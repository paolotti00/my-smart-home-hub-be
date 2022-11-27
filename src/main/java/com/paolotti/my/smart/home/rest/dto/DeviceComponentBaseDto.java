package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import lombok.ToString;

@ToString
public class DeviceComponentBaseDto {
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

    @Override
    public String toString() {
        return "DeviceComponentBaseDto{" +
                "id='" + id + '\'' +
                ", workingStatus=" + workingStatus +
                '}';
    }
}
