package com.paolotti.my.smart.home.exception;

import com.paolotti.my.smart.home.enums.DeviceInstallationStatusEnum;

public class DeviceWrongStatusException extends GenericException{
    public DeviceWrongStatusException(String deviceId, DeviceInstallationStatusEnum desiredStatus) {
        super("the device " + deviceId + "is not in " + desiredStatus + "status");
    }
}
