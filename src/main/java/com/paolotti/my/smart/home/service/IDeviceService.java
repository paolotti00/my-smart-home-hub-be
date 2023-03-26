package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.AckCommand;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.model.Device;

import java.util.List;

public interface IDeviceService {
    Device create(Device device) throws MissingFieldException;
    Device getDevice(String deviceId) throws DeviceNotExistsException, ValidationException;
    List<Device> getDevicesByUserId(String userId) throws ValidationException;
    List<Device> getDevicesByRoomId(String roomId) throws ValidationException, RoomNotExistsException;

    // light
    void switchAllLights(String userId, String deviceId, String roomId, OnOffStatusEnum desiredStatus, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException;
    void setLightColor(String userId, String deviceId, String roomId, List<Integer> colorRgbAndIntensity, CommandDestinationTypeEnum toDevice) throws GenericException;;

    // action
    List<ExtraActionCommandData> getSupportedExtraActions(String deviceId) throws BrandNotSupportedException, ValidationException, DeviceNotExistsException;
    void doExtraAction(String userId, String deviceId, String roomId, ExtraActionCommandData action, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException;

    // status
    void updateDeviceStatusFromAckReceived(AckCommand ackCommand) throws ValidationException, DeviceNotExistsException, BrandNotSupportedException;


    // command

//    void doFwActionsSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
//
//    List<ExtraActionCommandData> getSupportedActionsSchemas(String deviceId);
//
//    void doCustomActionSchema(String deviceId, DeviceActionsSchema deviceActionsSchema);
//
//    Device getActiveDeviceById(String deviceId) throws DeviceNotExistsException;
//
//    // command
//    void sendMqttCommandToAll(String topic, String payloadToEncapsulate) throws GenericException;
//
//    void sendMqttCommandToDevice(String topic, String payloadToEncapsulate, Device device) throws GenericException;
//
//    void sendMqttCommandToDeviceGroup(String topic, String payloadToEncapsulate, DeviceGroup deviceGroup) throws GenericException;
//
//    // status
//
//    void handleDeviceStatusFromPingReceived(PingDeviceStatus pingDeviceStatus) throws ValidationException, DeviceNotExistsException;
//
//
//    // light
//    void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws GenericException;
//
//    void setLightColor(String userId, String deviceId, String rgbColor) throws GenericException;
//
//    void doAction(String userId, String deviceId, ExtraActionCommandData action) throws GenericException;


}
