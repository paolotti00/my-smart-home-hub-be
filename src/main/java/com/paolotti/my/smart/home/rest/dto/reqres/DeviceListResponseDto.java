package com.paolotti.my.smart.home.rest.dto.reqres;

import java.util.ArrayList;

public class DeviceListResponseDto extends BaseResponseDto{
    private ArrayList devices;

    public ArrayList getDevices() {
        return devices;
    }

    public void setDevices(ArrayList devices) {
        this.devices = devices;
    }
}
