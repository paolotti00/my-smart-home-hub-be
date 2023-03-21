package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.exception.GenericException;
import com.paolotti.my.smart.home.mapper.deprecated.ILightEffectMessageMapper;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.Action;
import com.paolotti.my.smart.home.dto.ActionDto;
import com.paolotti.my.smart.home.dto.EffectDataDto;
import com.paolotti.my.smart.home.service.IDeviceByBrandService;
import com.paolotti.my.smart.home.service.IDeviceService;
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
    private static final String TOPIC_LIGHT = "light"; // todo add deviceId to topic
    private static final String TOPIC_LIGHT_EFFECT_BASE = "light_effect"; // todo add deviceId to topic
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
     * @param rgbColor the RGB color to set
     * @throws GenericException if an error occurs while setting the color
     */
    @Override
    public void setColor(Device device, String rgbColor) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ActionDto actionDto = new ActionDto(SET_COLOR_EFFECT_NAME, new EffectDataDto("1", new ArrayList<String>() {{
                add(rgbColor);
            }}));
            payload = objectMapper.writeValueAsString(actionDto);
            deviceService.sendMqttCommandToDevice(TOPIC_LIGHT+"/"+device.getThingId()+"/"+TOPIC_LIGHT_EFFECT_BASE,payload,device);
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
    public void doEffect(Device device, Action action) throws GenericException {
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            payload = objectMapper.writeValueAsString(lightEffectMessageMapper.toDto(action));
            deviceService.sendMqttCommandToDevice(TOPIC_LIGHT+"/"+device.getThingId()+"/"+TOPIC_LIGHT_EFFECT_BASE,payload,device);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("set action {} to device with id {} and brand paolotti", action.getName(), device.getId());
    }
}
