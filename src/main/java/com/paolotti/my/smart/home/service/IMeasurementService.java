package com.paolotti.my.smart.home.service;

import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.model.Measurement;

import java.time.LocalDateTime;
import java.util.List;

public interface IMeasurementService {
    List<Measurement> getMeasurementByRoomIdAndDeviceId(String roomId, String deviceId, MeasurementTypeEnum measurementTypeEnum, LocalDateTime from, LocalDateTime to) throws ValidationException;
    void handleByThingIdMeasurementReceived(String thingId, Measurement measurement) throws GenericException;
}
