package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;
import com.paolotti.my.smart.home.model.Device;

public class DeviceRegistrationResponseDto extends BaseResponseDto{
    private Device createdDevice;
    public Device getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(Device createdDevice) {
        this.createdDevice = createdDevice;
    }
}
