package com.paolotti.my.smart.home.service.deprecated;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public interface IMqttMessagingService {
    void publish(String topic, String payload, int qos, boolean retained)
            throws MqttPersistenceException, MqttException;

    void subscribe(String topic) throws MqttException, InterruptedException;
}
