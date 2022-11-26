package com.paolotti.my.smart.home.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Document(collection = "group_device")
public class GroupDeviceEntity extends GroupBase{
    @DocumentReference
    private List<DeviceEntity> devices;

    public List<DeviceEntity> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceEntity> devices) {
        this.devices = devices;
    }
}
