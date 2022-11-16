package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.BrandNotSupportedException;

public interface IDeviceLightService {
    void switchOnAllLightsByDevice(String userId,String deviceId) throws BrandNotSupportedException;
    void switchOffAllLightsByDevice(String userId,String deviceId);
    void switchOnAllLightsByGroup(String userId,String groupId);
    void switchOffAllLightsByGroup(String userId,String groupId);
}
