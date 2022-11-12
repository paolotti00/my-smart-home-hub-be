package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.Device;

public interface IDeviceService {
    <T extends Device> T deviceSelfRegistering(T device);
}
