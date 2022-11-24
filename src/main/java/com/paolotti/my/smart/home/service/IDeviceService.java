package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceActionsSchema;

import java.util.ArrayList;

public interface IDeviceService {
    public void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(String deviceId);
    public void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException;
    public ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException;
}
