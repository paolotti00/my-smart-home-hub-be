package com.paolotti.my.smart.home.exception;

public class DeviceNotExistsException extends GenericException {

    public DeviceNotExistsException(String deviceId) {
        super("deviceId " + deviceId + " not exist");
    }
}
