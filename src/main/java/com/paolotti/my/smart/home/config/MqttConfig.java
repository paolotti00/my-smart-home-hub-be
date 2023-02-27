package com.paolotti.my.smart.home.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions() {
        return new MqttConnectOptions();
    }

    @Bean
    public MqttAsyncClient mqttAsyncClient(@Value("${mqtt.clientId}") String clientId,
                                           @Value("${mqtt.hostname}") String hostname,
                                           @Value("${mqtt.port}") int port) throws MqttException {

        MqttAsyncClient mqttAsyncClient = new MqttAsyncClient("tcp://" + hostname + ":" + port, clientId);
        mqttAsyncClient.connect(mqttConnectOptions());
        return mqttAsyncClient;
    }

}