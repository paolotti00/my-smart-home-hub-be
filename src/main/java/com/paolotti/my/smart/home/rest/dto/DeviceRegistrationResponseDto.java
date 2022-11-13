package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.DeviceCreationResultStatusEnum;
import com.paolotti.my.smart.home.model.Device;

public class DeviceRegistrationResponseDto extends BaseResponseDto{
    private DeviceDto createdDevice;

    public DeviceDto getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(DeviceDto createdDevice) {
        this.createdDevice = createdDevice;
    }
}
