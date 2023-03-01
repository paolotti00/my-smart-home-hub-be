package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.*;

import java.util.ArrayList;

public interface IDeviceService {
    Device create(Device device) throws MissingFieldException;

    void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);

    ArrayList<DeviceActionsSchema> getSupportedActionsSchemas(String deviceId);

    void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);

    Device retrieveDeviceById(String deviceId) throws DeviceNotExistsException;

    ArrayList<Device> retrieveDevicesByGroupId(String groupId) throws GroupNotExistsException;

    // command
    void sendMqttCommandToAll(String topic, String payloadToEncapsulate) throws GenericException;

    void sendMqttCommandToDevice(String topic, String payloadToEncapsulate, Device device) throws GenericException;

    void sendMqttCommandToDeviceGroup(String topic, String payloadToEncapsulate, DeviceGroup deviceGroup) throws GenericException;

    // status
    void updateDeviceStatusFromAckReceived(CommandAck commandAck) throws ValidationException;


    // light
    void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws BrandNotSupportedException, DeviceNotExistsException;

    void setColor(String userId, String deviceId, String rgbColor) throws GenericException;

    void doAction(String userId, String deviceId, Action action) throws GenericException;
}
