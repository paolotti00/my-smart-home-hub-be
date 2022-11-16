package com.paolotti.my.smart.home.rest.dto.reqres;

import com.paolotti.my.smart.home.rest.dto.DeviceDto;

public class DeviceRegistrationResponseDto extends BaseResponseDto{
    private DeviceDto createdDevice;

    public DeviceDto getCreatedDevice() {
        return createdDevice;
    }

    public void setCreatedDevice(DeviceDto createdDevice) {
        this.createdDevice = createdDevice;
    }

    @Override
    public String toString() {
        return "DeviceRegistrationResponseDto{" +
                "resultStatus=" + resultStatus +
                ", message='" + message + '\'' +
                ", createdDevice=" + createdDevice +
                '}';
    }
}
