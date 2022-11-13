package com.paolotti.my.smart.home.rest.dto;

import java.util.ArrayList;

public class GetDevicesToActivateResponseDto extends BaseResponseDto{
    ArrayList<DeviceDto> devicesList;

    public ArrayList<DeviceDto> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(ArrayList<DeviceDto> devicesList) {
        this.devicesList = devicesList;
    }

    @Override
    public String toString() {
        return "GetDevicesToActivateResponseDto{" +
                "resultStatus=" + resultStatus +
                ", message='" + message + '\'' +
                ", devicesList=" + devicesList +
                '}';
    }
}
