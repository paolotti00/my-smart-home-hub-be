package com.paolotti.my.smart.home.mqtt.listener;

import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Service
public class TestListener {
    @Autowired
    IMqttMessagingService mqttMessagingService;
    @Autowired
    private IMqttClient mqttClient;
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);
    @PostConstruct
    void listener(){
        try {
            mqttClient.subscribeWithResponse("effects", (topic, msg) -> {
               handle(new String(msg.getPayload()));
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
    void handle(String message) throws MqttException {
        logger.info("msg ricevuto " + message);
        for (int i = 0; i < 5; i++) {
            String payload = message + " " + i;
            logger.info("mando " + payload);
            mqttMessagingService.publish("response",payload ,1,false);
        }

    }
}
