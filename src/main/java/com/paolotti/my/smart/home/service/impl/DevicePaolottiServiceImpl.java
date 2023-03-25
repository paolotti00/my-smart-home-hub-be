package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicePaolottiServiceImpl extends DeviceAbstractServiceImpl {
    @Autowired()
    IMqttMessagingService mqttMessagingService;
    private static final Logger logger = LoggerFactory.getLogger(DevicePaolottiServiceImpl.class);
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

    @Override
    public void switchAllLights(String userId, String deviceId, OnOffStatusEnum desiredStatus) throws GenericException {

    }

    @Override
    public void setLightColor(String userId, String deviceId, String rgbColor) throws GenericException {

    }

    @Override
    public void doExtraAction(String userId, String deviceId, String roomId, ExtraActionCommandData extraActionCommandData, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        // todo add log
        Device device = getDevice(deviceId);
        // todo do validation based on commandDestinationTypeEnum
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (commandDestinationTypeEnum){
                case TO_DEVICE:
                    sendMqttCommand(MQTT_TOPIC_IN_EFFECTS_DEVICE.replace("{thingId}",device.getThingId()),extraActionCommandData,deviceId,device.getThingId(),null,commandDestinationTypeEnum);
                    break;
                case TO_ROOM:
                    /* todo - idea:
                    *   send to device a list of thingId the device will checks if there is the own thing id and so do thing
                    *   when a device is added to a room send a message with the room id , so the device could list to some topic like command/room/{roomId}/light/effect/ in this case we have to save inside the device the room id also delete and so on*/
//                    sendMqttCommand(MQTT_TOPIC_IN_EFFECTS_DEVICE.replace("{thingId}",deviceThingId),extraActionCommandData,deviceId,deviceThingId,null);
            }
        } catch (GenericException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("set commandData {} to device with id {} and brand {}", extraActionCommandData.getName(), deviceId, DeviceBrandEnum.PAOLOTTI);

    }

    private <T> void sendMqttCommand(String topic, T rawData, String deviceId, String deviceThingId, String roomId, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        String logDeviceRoom = deviceId != null ? "device id " + deviceId : "roomId " + roomId;
        logger.info("sending mqtt command on topic {} with extraActionCommandData {} to {}", topic, rawData, logDeviceRoom);
        String commandId = null;
        String payload = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            commandId = String.valueOf(System.currentTimeMillis());
            Command command = new Command();
            command.setCommandId(commandId);
            command.setRawData(objectMapper.writeValueAsString(rawData));
            payload = objectMapper.writeValueAsString(commandMapper.toDto(command));
            mqttMessagingService.publish(topic, payload, 1, true);
            saveCommandInDb(command, deviceId,deviceThingId, roomId, commandDestinationTypeEnum);
            // todo save command on db - create entity
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("sent - mqtt commandId {} on topic {} with payload {} to {}", commandId, topic, payload, logDeviceRoom);
    }
}
