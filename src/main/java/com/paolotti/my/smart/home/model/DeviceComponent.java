package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import lombok.ToString;

@ToString
public class DeviceComponent{
        private String id;
        private DeviceTypeEnum type;
        private DeviceWorkingStatus workingStatus;
        private DeviceOperatingStatusEnum status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public DeviceOperatingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceOperatingStatusEnum status) {
        this.status = status;
    }

    public DeviceWorkingStatus getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(DeviceWorkingStatus workingStatus) {
        this.workingStatus = workingStatus;
    }
}
