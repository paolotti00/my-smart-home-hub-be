package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.model.DeviceStatus;
import com.paolotti.my.smart.home.model.Measurement;

public interface IWebSocketProducer {
    void sendUpdatedDeviceStatus(String thingId, DeviceStatus deviceStatus);
    void sendMeasurement(String roomId, String deviceId, Measurement measurement);
}
