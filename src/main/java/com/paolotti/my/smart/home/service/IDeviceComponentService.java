package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.model.DeviceComponent;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;

import java.util.ArrayList;

public interface IDeviceComponentService {
    void updateComponentsStatus(DeviceEntity deviceEntity, ArrayList<DeviceComponent> componentsToBeUpdate) throws DeviceNotExistsException;
}
