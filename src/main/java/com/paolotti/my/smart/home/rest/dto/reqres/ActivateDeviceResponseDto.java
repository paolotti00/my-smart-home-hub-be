package com.paolotti.my.smart.home.rest.dto.reqres;

import com.paolotti.my.smart.home.rest.dto.DeviceDto;

public class ActivateDeviceResponseDto extends BaseResponseDto{
    DeviceDto deviceDto;

    public DeviceDto getDeviceDto() {
        return deviceDto;
    }

    public void setDeviceDto(DeviceDto deviceDto) {
        this.deviceDto = deviceDto;
    }
}
