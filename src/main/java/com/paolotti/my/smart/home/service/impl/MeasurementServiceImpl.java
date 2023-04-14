package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.paolotti.my.smart.home.dto.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.enums.MeasurementTypeEnum;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.factory.IBeanFactoryDeviceService;
import com.paolotti.my.smart.home.mapper.IMeasurementMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Measurement;
import com.paolotti.my.smart.home.repository.MeasurementRepository;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.repository.entity.MeasurementEntity;
import com.paolotti.my.smart.home.service.IDeviceService;
import com.paolotti.my.smart.home.service.IMeasurementService;
import com.paolotti.my.smart.home.service.IWebSocketProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementServiceImpl implements IMeasurementService {
    @Autowired
    IMeasurementMapper measurementMapper;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    IBeanFactoryDeviceService beanFactoryDeviceService;
    @Autowired
    IWebSocketProducer webSocketProducer;
    private static final Logger logger = LoggerFactory.getLogger(MeasurementServiceImpl.class);

    @Override
    public List<Measurement> getMeasurementByRoomIdAndDeviceId(String roomId, String deviceId, MeasurementTypeEnum measurementTypeEnum, LocalDateTime from, LocalDateTime to) throws ValidationException {
        logger.info("retrieving measurements for room with id {} device with id {} and type {} from {} to {}",roomId,deviceId,measurementTypeEnum,from,to);
        List<Measurement> measurementsList = new ArrayList<>();
        if(StringUtils.isEmpty(roomId) && StringUtils.isEmpty(deviceId)){
            throw new ValidationException("roomId and deviceId cannot be null both");
        }
        Optional<List<MeasurementEntity>> measurementEntityListOpt = measurementRepository.findByRoomIdAndDeviceIdAndTypeAndFromAndTo(roomId,deviceId,measurementTypeEnum,from,to);
        if(measurementEntityListOpt.isPresent()){
            measurementsList = measurementMapper.toModelList(measurementEntityListOpt.get());
        } else {
            logger.warn("no measurements found for room with id {}", roomId);
        }
        logger.info("retrieved measurements {} for room with id {}",measurementsList,roomId);
        return measurementsList;
    }

    @Override
    public void handleByThingIdMeasurementReceived(String thingId, Measurement measurement) throws GenericException {
        logger.info("handling measurement received by thing id {}",thingId);
        IDeviceService deviceService = beanFactoryDeviceService.getDeviceServiceByThingId(thingId);
        Device device = deviceService.getDeviceByThingId(thingId);
        // converting measurement to measurement entity
        MeasurementEntity measurementEntity = measurementMapper.toEntity(measurement);
        measurementEntity.setRoomId(device.getRoomId());
        measurementEntity.setDeviceId(device.getId());
        measurementRepository.save(measurementEntity);
        // sending new measurement to websocket
        webSocketProducer.sendMeasurement(device.getRoomId(), device.getId(),measurement);
        logger.info("measurement received by thing id {} correctly handled",thingId);
    }
}
