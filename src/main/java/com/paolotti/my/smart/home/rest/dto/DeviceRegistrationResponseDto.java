package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;
import com.paolotti.my.smart.home.model.Device;

public class DeviceRegistrationResponseDto {
    private DeviceCreationResultStatusEnum creationResult;
    private String creationErrorMsg;
    private Device createdDevice;

    public DeviceCreationResultStatusEnum getCreationResult() {
        return creationResult;
    }

    public void setCreationResult(DeviceCreationResultStatusEnum creationResult) {
        this.creationResult = creationResult;
    }

    public String getCreationErrorMsg() {
        return creationErrorMsg;
    }

    public void setCreationErrorMsg(String creationErrorMsg) {
        this.creationErrorMsg = creationErrorMsg;
    }

    public Device getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(Device createdDevice) {
        this.createdDevice = createdDevice;
    }
}
