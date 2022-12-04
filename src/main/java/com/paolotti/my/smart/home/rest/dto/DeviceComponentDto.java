package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import lombok.ToString;

@ToString
public class DeviceComponentDto {
    private String id;
    private DeviceTypeEnum type;
    private DeviceWorkingStatusDto workingStatus;
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

    public DeviceWorkingStatusDto getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(DeviceWorkingStatusDto workingStatus) {
        this.workingStatus = workingStatus;
    }

    public DeviceOperatingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceOperatingStatusEnum status) {
        this.status = status;
    }
}
