package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GroupNotExistsException;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceActionsSchema;

import java.util.ArrayList;

public interface IDeviceService {
    public void doFwActionsSchema(Device device, DeviceActionsSchema deviceActionsSchema);
    public ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(Device device);
    public void doCustomActionSchema(Device device, DeviceActionsSchema deviceActionsSchema);
    public Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException;
    public ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException;
}
