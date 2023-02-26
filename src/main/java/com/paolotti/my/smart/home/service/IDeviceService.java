package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.*;

import java.util.ArrayList;

public interface IDeviceService {
    Device create(Device device) throws  MissingFieldException;
    public void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(String deviceId);
    public void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
    public Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException;
    public ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException;

    // command
    void sendMqttCommandToAll(String topic, String payloadToEncapsulate) throws GenericException;
    void sendMqttCommandToDevice(String topic, String payloadToEncapsulate, Device device) throws GenericException;

    void sendMqttCommandToDeviceGroup(String topic, String payloadToEncapsulate, DeviceGroup deviceGroup) throws GenericException;

    // light
    void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException;
    void setColor(String userId, String deviceId, ColorRgb colorRgb) throws BrandNotSupportedException, DeviceNotExistsException, GenericException;
    void doAction(String userId, String deviceId, Action action) throws BrandNotSupportedException, DeviceNotExistsException, GenericException;
}
