package com.paolotti.my.smart.home.mqtt.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.mqtt.CommandAckDto;
import com.paolotti.my.smart.home.exception.ValidationException;
import com.paolotti.my.smart.home.mapper.ICommandAckMapper;
import com.paolotti.my.smart.home.model.CommandAck;
import com.paolotti.my.smart.home.service.IDeviceService;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AckListener {
    @Autowired
    private MqttAsyncClient mqttAsyncClient;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private ICommandAckMapper commandAckMapper;
    private static final Logger logger = LoggerFactory.getLogger(AckListener.class);
    @Value("${mqtt.topic.command.ack}")
    private String ackTopic;

    @EventListener(ApplicationReadyEvent.class)
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
    void handle(String message) throws MqttException, JsonProcessingException {
        logger.info("received: {} on topic {} : ",message,ackTopic);
        CommandAckDto commandAckDto = new ObjectMapper().readValue(message, CommandAckDto.class);
        CommandAck commandAck = commandAckMapper.toModel(commandAckDto);
        try {
            deviceService.updateDeviceStatusFromAckReceived(commandAck);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
