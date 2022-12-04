package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.enums.DeviceOperatingStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import lombok.ToString;

@ToString
public class DeviceComponentDto {
    @JsonView(JsonViewConfig.HighDetail.class)
    private String id;
    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceTypeEnum type;
    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceWorkingStatusDto workingStatus;
    @JsonView(JsonViewConfig.LowDetail.class)
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
