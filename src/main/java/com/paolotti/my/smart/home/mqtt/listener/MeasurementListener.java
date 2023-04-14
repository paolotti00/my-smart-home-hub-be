package com.paolotti.my.smart.home.mqtt.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.MeasurementDto;
import com.paolotti.my.smart.home.mapper.IMeasurementMapper;
import com.paolotti.my.smart.home.model.Measurement;
import com.paolotti.my.smart.home.service.IMeasurementService;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class MeasurementListener {
    @Autowired
    private MqttAsyncClient mqttAsyncClient;
    @Autowired
    private IMeasurementMapper measurementMapper;
    @Autowired
    private IMeasurementService measurementService;
    private static final Logger logger = LoggerFactory.getLogger(MeasurementListener.class);
    @Value("${mqtt.topic.measurements}")
    private String ackTopic;

    @EventListener(ApplicationReadyEvent.class)
    @Retryable(include = { MqttException.class }, maxAttempts = 3, backoff = @Backoff(delay = 5000L))
    public void listener() throws MqttException {
        mqttAsyncClient.subscribe(ackTopic, 1, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                logger.info("successfully subscribed topic {}",ackTopic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                logger.warn("failed to subscribe topic {}",ackTopic);
            }
        }, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                handle(message.toString());
            }
        });
    }
    void handle(String message) throws MqttException {
        logger.info("received: {} on topic {} : ",message,ackTopic);
        try {
            MeasurementDto measurementDto = new ObjectMapper().readValue(message, MeasurementDto.class);
            Measurement measurement = measurementMapper.toModel(measurementDto);
            measurementService.handleByThingIdMeasurementReceived(measurementDto.getThingId(),measurement);
        } catch (Exception e) {
            // todo
            e.printStackTrace();
        }
    }
    @Recover
    public void recover(MqttException e) {
        logger.error("failed to connect to mqtt client: {}",e.getMessage());
    }
}
