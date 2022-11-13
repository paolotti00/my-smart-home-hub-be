package com.paolotti.my.smart.home.rest.dto;

public class ActivateDeviceResponseDto extends BaseResponseDto{
    DeviceDto deviceDto;

    public DeviceDto getDeviceDto() {
        return deviceDto;
    }

    public void setDeviceDto(DeviceDto deviceDto) {
        this.deviceDto = deviceDto;
    }
}
