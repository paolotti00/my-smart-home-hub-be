package com.paolotti.my.smart.home.service.impl;

import com.paolotti.my.smart.home.rest.impl.DeviceRestControllerImpl;
import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttMessagingServiceImpl implements IMqttMessagingService {
    private static final Logger logger = LoggerFactory.getLogger(MqttMessagingServiceImpl.class);
    @Autowired
    private IMqttClient mqttClient;

    @Override
    public void publish(final String topic, final String payload, int qos, boolean retained)
            throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        logger.info("sending to topic {} qos {} retained {} payload {}",topic,qos,retained,payload);
        mqttClient.publish(topic, mqttMessage);
    }

    @Override
    public void subscribe(final String topic) throws MqttException, InterruptedException {
        System.out.println("Messages received:");


        mqttClient.subscribe(topic, (tpic, msg) -> {
            System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
        });
    }
}
