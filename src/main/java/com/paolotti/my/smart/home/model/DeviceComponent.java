package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import lombok.ToString;

@ToString
public class DeviceComponent{
        private String id;
        private DeviceComponentTypeEnum type;
        private DeviceComponentWorkingStatus workingStatus;
        private DeviceOperatingStatusEnum status;
        private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceComponentTypeEnum getType() {
        return type;
    }

    public void setType(DeviceComponentTypeEnum type) {
        this.type = type;
    }

    public DeviceOperatingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceOperatingStatusEnum status) {
        this.status = status;
    }

    public DeviceComponentWorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(DeviceComponentWorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
