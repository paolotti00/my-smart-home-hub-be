package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationRequestDto;
import com.paolotti.my.smart.home.rest.dto.DeviceRegistrationResponseDto;

public interface IDeviceService {

    DeviceRegistrationResponseDto deviceSelfRegisteringHandling(DeviceRegistrationRequestDto deviceRegistrationRequestDto);
}
