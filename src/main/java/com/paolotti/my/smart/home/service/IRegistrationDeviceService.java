package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.reqres.DeviceRegistrationResponseDto;

import java.util.ArrayList;

public interface IRegistrationDeviceService {

    DeviceRegistrationResponseDto deviceSelfRegisteringHandling(String userId, DeviceRegistrationRequestDto deviceRegistrationRequestDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException;

    ArrayList<DeviceDto> getDeviceToActivate(String userid) throws MissingFieldException, UserNotExistException;

    DeviceDto activate(String userId, String deviceId) throws MissingFieldException, UserNotExistException, DeviceNotExistsException, DeviceAlreadyActivated, DeviceWrongStatusException;

    Device getDeviceById(String deviceId) throws DeviceNotExistsException;

    Device checkIfDeviceExistsAndRetrieve(String deviceId) throws DeviceNotExistsException;
}
