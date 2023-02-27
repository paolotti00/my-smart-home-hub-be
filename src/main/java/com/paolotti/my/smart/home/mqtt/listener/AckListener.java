package com.paolotti.my.smart.home.mqtt.listener;

import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class AckListener {
    @Autowired
    private MqttAsyncClient mqttAsyncClient;
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
    void handle(String message) throws MqttException {
        logger.info("msg ricevuto " + message);

    }
}
