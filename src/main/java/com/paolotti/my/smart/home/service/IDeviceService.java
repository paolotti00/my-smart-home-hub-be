package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.DeviceRegistrationRequest;
import com.paolotti.my.smart.home.model.DeviceRegistrationResponse;

public interface IDeviceService {

    DeviceRegistrationResponse deviceSelfRegisteringHandling(DeviceRegistrationRequest deviceRegistrationRequest);
}
