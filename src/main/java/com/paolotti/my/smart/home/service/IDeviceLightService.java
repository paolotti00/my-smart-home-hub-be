package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.BrandNotSupportedException;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.model.Device;

public interface IDeviceLightService {

    void switchAllLightsByDevice(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException;

    void switchAllLightsByGroup(String userId, String groupId, OnOffStatusEnum desiredStatus) throws GroupNotExistsException;
}
