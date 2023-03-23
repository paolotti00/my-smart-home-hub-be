package com.paolotti.my.smart.home.service.deprecated.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.dto.deprecated.mqtt.SwitchOnOffStatusDto;
import com.paolotti.my.smart.home.enums.deprecated.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.mapper.deprecated.ILightEffectMessageMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Action;
import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.deprecated.EffectDataDto;
import com.paolotti.my.smart.home.service.deprecated.IDeviceByBrandService;
import com.paolotti.my.smart.home.service.deprecated.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DeviceByBrandPaolottiServiceImpl implements IDeviceByBrandService {
    @Autowired
    ILightEffectMessageMapper lightEffectMessageMapper;
    @Autowired
    IDeviceService deviceService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceByBrandPaolottiServiceImpl.class);
    private static final String MQTT_TOPIC_COMMAND_BASE="command";
    private static final String MQTT_SUB_TOPIC_DEVICE="/device/{thingId}";
    private static final String MQTT_SUB_TOPIC_LIGHT="/light";
    private static final String MQTT_SUB_TOPIC_ARGUMENT_EFFECT="/effect";
    private static final String MQTT_SUB_TOPIC_ARGUMENT_SWITCH="/switch";

    private static final String MQTT_TOPIC_IN_EFFECTS_PUBLIC = MQTT_TOPIC_COMMAND_BASE+MQTT_SUB_TOPIC_LIGHT+MQTT_SUB_TOPIC_ARGUMENT_EFFECT; // command/light/effect
    private static final String MQTT_TOPIC_IN_EFFECTS_DEVICE = MQTT_TOPIC_COMMAND_BASE+MQTT_SUB_TOPIC_DEVICE+MQTT_SUB_TOPIC_LIGHT+MQTT_SUB_TOPIC_ARGUMENT_EFFECT; //command/device/{thingId}/light/effect/
    private static final String MQTT_TOPIC_IN_SWITCH_PUBLIC =  MQTT_TOPIC_COMMAND_BASE+MQTT_SUB_TOPIC_LIGHT+MQTT_SUB_TOPIC_ARGUMENT_SWITCH; // command/light/switch
    private static final String MQTT_TOPIC_IN_SWITCH_DEVICE =  MQTT_TOPIC_COMMAND_BASE+MQTT_SUB_TOPIC_DEVICE+MQTT_SUB_TOPIC_LIGHT+MQTT_SUB_TOPIC_ARGUMENT_SWITCH; //command/device/{thingId}/light/switch/


    private static final String TOPIC_LIGHT = "light"; // todo add deviceId to topic
    private static final String TOPIC_LIGHT_EFFECT_BASE = "light_effect"; // todo add deviceId to topic
    private static final String SET_COLOR_EFFECT_NAME = "simple_color";

    /**
     * Turn on/off the device with the given ID and brand "paolotti".
     *
     * @param device the device to turn on
     * @param onOffStatusEnum
     */
    @Override
    public void switchLight(Device device, OnOffStatusEnum onOffStatusEnum) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SwitchOnOffStatusDto switchOnOffStatusDto = new SwitchOnOffStatusDto(onOffStatusEnum);
            payload = objectMapper.writeValueAsString(switchOnOffStatusDto);
            deviceService.sendMqttCommandToDevice(MQTT_TOPIC_IN_SWITCH_DEVICE.replace("{thingId}",device.getThingId()),payload,device);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("device with id {} and brand paolotti is ON!!", device.getId());
    }

    /**
     * Set the color of the device with the given ID and brand "paolotti".
     *
     * @param device   the device to set the color for
     * @param rgbColor the RGB color to set
     * @throws GenericException if an error occurs while setting the color
     */
    @Override
    public void setLightColor(Device device, String rgbColor) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ActionDto actionDto = new ActionDto(SET_COLOR_EFFECT_NAME, new EffectDataDto("1", new ArrayList<String>() {{
                add(rgbColor);
            }}));
            payload = objectMapper.writeValueAsString(actionDto);
            deviceService.sendMqttCommandToDevice(MQTT_TOPIC_IN_EFFECTS_DEVICE.replace("{thingId}",device.getThingId()),payload,device);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("device with id {} and brand paolotti has been set to {} color!!", device.getId(), rgbColor);
    }

/**
 * Perform a light effect on the device with the given ID and brand "paolotti".
 *
 * @param device              the device to perform the effect on
 * @param action the light effect message to send
 * @throws GenericException if an error occurs while performing the effect*/

    @Override
    public void doLightEffect(Device device, Action action) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.writeValueAsString(lightEffectMessageMapper.toDto(action));
            deviceService.sendMqttCommandToDevice(MQTT_TOPIC_IN_EFFECTS_DEVICE.replace("{thingId}",device.getThingId()),payload,device);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("set action {} to device with id {} and brand paolotti", action.getName(), device.getId());
    }
}
