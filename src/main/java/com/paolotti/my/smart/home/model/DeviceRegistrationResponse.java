package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;

public class DeviceRegistrationResponse extends BaseResponse {

    private Device createdDevice;
    public Device getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(Device createdDevice) {
        this.createdDevice = createdDevice;
    }
}
