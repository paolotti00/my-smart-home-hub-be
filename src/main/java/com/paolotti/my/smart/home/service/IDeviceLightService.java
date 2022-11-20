package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.model.Device;

public interface IDeviceLightService {
    void switchOnAllLightsByDevice(String userId, String deviceId) throws BrandNotSupportedException, DeviceNotExistsException;
    void switchOffAllLightsByDevice(String userId,String deviceId) throws BrandNotSupportedException, DeviceNotExistsException;
    void switchOnAllLightsByGroup(String userId,String groupId) throws GroupNotExistsException;
    void switchOffAllLightsByGroup(String userId,String groupId) throws GroupNotExistsException;
}
