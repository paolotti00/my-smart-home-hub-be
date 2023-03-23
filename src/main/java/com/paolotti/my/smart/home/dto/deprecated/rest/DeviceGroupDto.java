package com.paolotti.my.smart.home.dto.deprecated.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import com.paolotti.my.smart.home.dto.rest.DeviceDto;
import lombok.ToString;

import java.util.List;

@ToString
public class DeviceGroupDto extends GroupBaseDto{
    @JsonView(JsonViewConfig.LowDetail.class)
    private List<DeviceDto> devices;

    public List<DeviceDto> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceDto> devices) {
        this.devices = devices;
    }
}
