package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceComponentActionsSchema;

import java.util.ArrayList;

public interface IDeviceService {
    public void doFwActionsSchema(Device device, DeviceComponentActionsSchema deviceComponentActionsSchema);
    public ArrayList<DeviceComponentActionsSchema> getSupportedActionsSchemas(Device device);
    public void doCustomActionSchema(Device device,DeviceComponentActionsSchema deviceComponentActionsSchema);
}
