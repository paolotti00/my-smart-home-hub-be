package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceActionsSchema;

import java.util.ArrayList;

public interface IDeviceService {
    Device create(Device device) throws  MissingFieldException;
    public void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(String deviceId);
    public void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException;
    public ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException;
    // light
    void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException;
    void setColor(String userId, String deviceId, ColorRgb colorRgb) throws BrandNotSupportedException, DeviceNotExistsException, GenericException;
}
