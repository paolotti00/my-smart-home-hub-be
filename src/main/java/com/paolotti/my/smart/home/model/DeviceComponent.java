package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceConnectionStatusEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import lombok.ToString;

@ToString
public class DeviceComponent{
    private String id;
    private DeviceComponentTypeEnum type;
    private OnOffStatusEnum status;
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

    public OnOffStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OnOffStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
