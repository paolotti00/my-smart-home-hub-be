package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.mapper.ILightEffectMessageMapper;
import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.LightEffectMessage;
import com.paolotti.my.smart.home.rest.dto.mqtt.EffectDataDto;
import com.paolotti.my.smart.home.rest.dto.mqtt.LightEffectMessageDto;
import com.paolotti.my.smart.home.service.IDeviceByBrandService;
import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeviceByBrandPaolottiServiceImpl implements IDeviceByBrandService {

    @Autowired
    IMqttMessagingService mqttMessagingService;
    @Autowired
    ILightEffectMessageMapper lightEffectMessageMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceByBrandPaolottiServiceImpl.class);
    private static final String EFFECT_TOPIC = "light_effect"; // todo add deviceId to topic
    private static final String SET_COLOR_EFFECT_NAME = "simple_color";

    /**
     * Turn on the device with the given ID and brand "paolotti".
     *
     * @param device the device to turn on
     */
    @Override
    public void switchOn(Device device) {
        logger.info("device with id {} and brand paolotti is ON!!", device.getId());
    }

    /**
     * Turn off the device with the given ID and brand "paolotti".
     *
     * @param device the device to turn off
     */
    @Override
    public void switchOff(Device device) {
        logger.info("device with id {} and brand paolotti is OFF!!", device.getId());
    }

    /**
     * Set the color of the device with the given ID and brand "paolotti".
     *
     * @param device   the device to set the color for
     * @param colorRgb the RGB color to set
     * @throws GenericException if an error occurs while setting the color
     */
    @Override
    public void setColor(Device device, ColorRgb colorRgb) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LightEffectMessageDto lightEffectMessageDto = new LightEffectMessageDto(SET_COLOR_EFFECT_NAME, new EffectDataDto("1", new ArrayList<String>() {{
                add(colorRgb.getRgbAsAString());
            }}));
            payload = objectMapper.writeValueAsString(lightEffectMessageDto);
            mqttMessagingService.publish(EFFECT_TOPIC, payload, 1, true);
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("device with id {} and brand paolotti has been set to {} color!!", device.getId(), colorRgb);
    }

/**
 * Perform a light effect on the device with the given ID and brand "paolotti".
 *
 * @param device              the device to perform the effect on
 * @param lightEffectMessage the light effect message to send
 * @throws GenericException if an error occurs while performing the effect*/

    @Override
    public void doEffect(Device device, LightEffectMessage lightEffectMessage) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.writeValueAsString(lightEffectMessageMapper.toDto(lightEffectMessage));
            mqttMessagingService.publish(EFFECT_TOPIC, payload, 1, true);
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("set action {} to device with id {} and brand paolotti", lightEffectMessage.getName(), device.getId());
    }
}
