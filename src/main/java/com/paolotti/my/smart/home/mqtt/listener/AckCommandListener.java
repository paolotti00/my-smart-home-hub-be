package com.paolotti.my.smart.home.mqtt.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.deprecated.mqtt.AckCommandDto;
import com.paolotti.my.smart.home.mapper.deprecated.IAckCommandMapper;
import com.paolotti.my.smart.home.model.deprecated.AckCommand;
import com.paolotti.my.smart.home.service.deprecated.IDeviceService;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AckCommandListener {
    @Autowired
    private MqttAsyncClient mqttAsyncClient;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IAckCommandMapper commandAckMapper;
    private static final Logger logger = LoggerFactory.getLogger(AckCommandListener.class);
    @Value("${mqtt.topic.ack.command}")
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
    void handle(String message) throws MqttException {
        logger.info("received: {} on topic {} : ",message,ackTopic);
        try {
            AckCommandDto ackCommandDto = new ObjectMapper().readValue(message, AckCommandDto.class);
            AckCommand ackCommand = commandAckMapper.toModel(ackCommandDto);
            deviceService.updateDeviceStatusFromAckReceived(ackCommand);
        } catch (Exception e) {
            // todo
            e.printStackTrace();
        }
    }
}
