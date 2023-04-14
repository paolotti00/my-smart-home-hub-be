package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.dto.MeasurementDto;
import com.paolotti.my.smart.home.dto.mqtt.DeviceStatusDto;
import com.paolotti.my.smart.home.exception.DeviceNotExistsException;
import com.paolotti.my.smart.home.mapper.IDeviceStatusMapper;
import com.paolotti.my.smart.home.mapper.IMeasurementMapper;
import com.paolotti.my.smart.home.model.DeviceStatus;
import com.paolotti.my.smart.home.model.Measurement;
import com.paolotti.my.smart.home.repository.DeviceRepository;
import com.paolotti.my.smart.home.service.IWebSocketProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketProducerImpl implements IWebSocketProducer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketProducerImpl.class);
    private static final String DEVICE_STATUS_UPDATED_WEBSOCKET_TOPIC = "/topic/device/{deviceId}/status"; // todo move in properties file
    private static final String DEVICE_MEASUREMENT_WEBSOCKET_TOPIC = "/topic/device/{deviceId}/measurement"; // todo move in properties file
    private static final String ROOM_MEASUREMENT_WEBSOCKET_TOPIC = "/topic/room/{roomId}/measurement"; // todo move in properties file
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    DeviceRepository deviceRepository;
    @Autowired
    IDeviceStatusMapper deviceStatusMapper;
    @Autowired
    IMeasurementMapper measurementMapper;

    @Override
    public void sendUpdatedDeviceStatus(String deviceId, DeviceStatus deviceStatus) {
        logger.info("sending, by websocket, for deviceId {} updateStatus {}", deviceId, deviceStatus);
        DeviceStatusDto deviceStatusDto = deviceStatusMapper.toDto(deviceStatus);
        simpMessagingTemplate.convertAndSend(DEVICE_STATUS_UPDATED_WEBSOCKET_TOPIC.replace("{deviceId}", deviceId), deviceStatusDto);
        logger.info("updateStatus by websocket for thingId {} was sent correctly", deviceId);
    }


    @Override
    public void sendMeasurement(String roomId, String deviceId, Measurement measurement) {
        logger.info("sending, by websocket, for deviceId {} and roomId {} measurement {}", roomId,deviceId,measurement);
        MeasurementDto measurementDto = measurementMapper.toDto(measurement);
        String deviceTopic = DEVICE_MEASUREMENT_WEBSOCKET_TOPIC.replace("{deviceId}", deviceId);
        String roomTopic = ROOM_MEASUREMENT_WEBSOCKET_TOPIC.replace("{roomId}",roomId);
        logger.debug("sending to {}",deviceTopic);
        simpMessagingTemplate.convertAndSend(deviceTopic, measurementDto);
        logger.debug("sending to {}",roomTopic);
        simpMessagingTemplate.convertAndSend(roomTopic, measurementDto);
        logger.info("measurement by websocket for deviceId {} and roomId {} was sent correctly", deviceId,roomId);
    }
}
