package com.paolotti.my.smart.home.mqtt.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.mqtt.PingDeviceStatusDto;
import com.paolotti.my.smart.home.mapper.IPingDeviceStatusMapper;
import com.paolotti.my.smart.home.model.PingDeviceStatus;
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
public class PingDeviceStatusListener {
    @Autowired
    private MqttAsyncClient mqttAsyncClient;
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IPingDeviceStatusMapper pingDeviceStatusMapper;
    private static final Logger logger = LoggerFactory.getLogger(PingDeviceStatusListener.class);
    @Value("${mqtt.topic.ping.device.status}")
    private String pingTopic;

    @EventListener(ApplicationReadyEvent.class)
    public void listener() throws MqttException {
        mqttAsyncClient.subscribe(pingTopic, 1, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                logger.info("successfully subscribed topic {}",pingTopic);
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                logger.warn("failed to subscribe topic {}",pingTopic);
            }
        }, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                handle(message.toString());
            }
        });
    }

    void handle(String message) throws MqttException {
        logger.info("received: {} on topic {} : ",pingTopic,message);
        try {
            PingDeviceStatusDto pingDeviceStatusDto = new ObjectMapper().readValue(message, PingDeviceStatusDto.class);
            PingDeviceStatus pingDeviceStatus = pingDeviceStatusMapper.toModel(pingDeviceStatusDto);
            deviceService.handleDeviceStatusFromPingReceived(pingDeviceStatus);
        } catch (Exception e) {
            // todo
            e.printStackTrace();
        }
    }
}
