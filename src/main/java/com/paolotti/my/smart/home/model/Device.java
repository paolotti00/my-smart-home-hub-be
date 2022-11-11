package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceTypeEnum;

public class Device {
    private String name;
    private String id;
    DeviceTypeEnum type;
    DeviceStatusEnum status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceTypeEnum getType() {
        return type;
    }

    public void setType(DeviceTypeEnum type) {
        this.type = type;
    }

    public DeviceStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeviceStatusEnum status) {
        this.status = status;
    }
}
