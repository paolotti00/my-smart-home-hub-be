package com.paolotti.my.smart.home.exception;

import com.paolotti.my.smart.home.constant.MessageConst;

public class DeviceAlreadyActivated extends GenericException{

    public DeviceAlreadyActivated(String deviceId) {
        super("deviceId " + deviceId +" "+ MessageConst.DEVICE_ALREADY_ACTIVATED);
    }
}
