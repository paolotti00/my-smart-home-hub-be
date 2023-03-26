package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.AckCommand;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.PingDeviceStatus;

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
    void handleDeviceStatusFromPingReceived(PingDeviceStatus pingDeviceStatus) throws ValidationException, DeviceNotExistsException, BrandNotSupportedException;

}
