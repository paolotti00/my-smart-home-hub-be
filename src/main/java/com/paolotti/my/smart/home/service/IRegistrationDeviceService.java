package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;

import java.util.ArrayList;

public interface IRegistrationDeviceService {

    Device deviceSelfRegisteringHandling(String userId, DeviceRegistrationRequest deviceRegistrationRequest) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException;
    ArrayList<Device> getDeviceToActivate(String userid) throws MissingFieldException, UserNotExistException;
    Device activate(String userId, String deviceId) throws MissingFieldException, UserNotExistException, DeviceNotExistsException, DeviceAlreadyActivated, DeviceWrongStatusException;
    Device getDeviceById(String deviceId) throws DeviceNotExistsException;
    Device checkIfDeviceExistsAndRetrieve(String deviceId) throws DeviceNotExistsException;
}
