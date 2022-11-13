package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.exception.DeviceAlreadyRegisteredException;
import com.paolotti.my.smart.home.exception.DeviceCreationException;
import com.paolotti.my.smart.home.exception.MissingFieldException;
import com.paolotti.my.smart.home.exception.UserNotExistException;
import com.paolotti.my.smart.home.rest.dto.DeviceDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;

import java.util.ArrayList;

public interface IDeviceRegistrationService {

    DeviceRegistrationResponseDto deviceSelfRegisteringHandling(String userId, DeviceRegistrationRequestDto deviceRegistrationRequestDto) throws DeviceAlreadyRegisteredException, MissingFieldException, DeviceCreationException, UserNotExistException;

    ArrayList<DeviceDto> getDeviceToActivate(String userid) throws MissingFieldException, UserNotExistException;
}
