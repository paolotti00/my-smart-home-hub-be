package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceConnectionStatusEnum;
import lombok.ToString;

@ToString
public class DeviceComponent{
    private String id;
    private DeviceComponentTypeEnum type;
    private DeviceConnectionStatusEnum connectionStatus;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceComponentTypeEnum getType() {
        return type;
    }

    public void setType(DeviceComponentTypeEnum type) {
        this.type = type;
    }

    public DeviceConnectionStatusEnum getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(DeviceConnectionStatusEnum connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
