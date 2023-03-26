package com.paolotti.my.smart.home.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.ConnectionStatusEnum;
import com.paolotti.my.smart.home.enums.DeviceBrandEnum;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.exception.*;
import com.paolotti.my.smart.home.model.*;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import com.paolotti.my.smart.home.service.IMqttMessagingService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DevicePaolottiServiceImpl extends DeviceAbstractServiceImpl {
    @Autowired()
    IMqttMessagingService mqttMessagingService;
    private static final Logger logger = LoggerFactory.getLogger(DevicePaolottiServiceImpl.class);
    private static final String MQTT_TOPIC_COMMAND_BASE = "command";
    private static final String MQTT_SUB_TOPIC_DEVICE = "/device/{thingId}";

    private static final String MQTT_TOPIC_IN_COMMAND_PUBLIC = MQTT_TOPIC_COMMAND_BASE; // command/
    private static final String MQTT_TOPIC_IN_COMMAND_DEVICE = MQTT_TOPIC_COMMAND_BASE + MQTT_SUB_TOPIC_DEVICE; // command/device/{thingId}


    private static final String ACTION_SWITCH_LIGHT_NAME = "switch_light";
    private static final String ACTION_SWITCH_LIGHT_FIELD = "status";
    private static final String ACTION_SET_LIGHT_COLOR_NAME = "set_light_color";
    private static final String ACTION_SET_LIGHT_FIELD_COLOR = "color";
    private static final String ACTION_SET_LIGHT_FIELD_INTENSITY = "intensity";

    @Override
    public void switchAllLights(String userId, String deviceId, String roomId, OnOffStatusEnum desiredStatus, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        logger.info("Trying to switch light {} on {}", desiredStatus, deviceId!=null? "device " + deviceId : "room" + roomId);
        Device device = getDevice(deviceId);
        // todo do validation based on commandDestinationTypeEnum
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // preparing fields of action switch
            ExtraActionCommandData.Field field = new ExtraActionCommandData.Field();
            field.setName(ACTION_SWITCH_LIGHT_FIELD);
            field.setValue(desiredStatus.name());
            // preparing fields of action switch
            ExtraActionCommandData extraActionCommandData = new ExtraActionCommandData();
            extraActionCommandData.setName(ACTION_SWITCH_LIGHT_NAME);
            extraActionCommandData.setFields(new ArrayList<>(Collections.singletonList(field)));
            doExtraAction(userId, deviceId, null, extraActionCommandData, commandDestinationTypeEnum);
        } catch (GenericException e) {
            logger.error("An error occurred while switching the light: " + e.getMessage());
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("Device with id {} and brand Paolotti is now {}.", device.getId(), desiredStatus.name());
    }

    @Override
    public void setLightColor(String userId, String deviceId, String roomId, List<Integer> colorRgbAndIntensity, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        logger.info("Trying to set color {} on {}", colorRgbAndIntensity, deviceId!=null? "device " + deviceId : "room" + roomId);
        Device device = getDevice(deviceId);
        // todo do validation based on commandDestinationTypeEnum
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // preparing action set color
            ExtraActionCommandData extraActionCommandData = new ExtraActionCommandData();
            extraActionCommandData.setName(ACTION_SET_LIGHT_COLOR_NAME);
            extraActionCommandData.setFields(new ArrayList<>());
            // preparing fields of action switch
            ExtraActionCommandData.Field fieldColor = new ExtraActionCommandData.Field();
            fieldColor.setName(ACTION_SET_LIGHT_FIELD_COLOR);
            fieldColor.setValue(colorRgbAndIntensity.get(0) + "," + colorRgbAndIntensity.get(1) + "," + colorRgbAndIntensity.get(2));
            extraActionCommandData.getFields().add(fieldColor);
            if(colorRgbAndIntensity.size()>3){
                ExtraActionCommandData.Field fieldIntensity = new ExtraActionCommandData.Field();
                fieldIntensity.setName(ACTION_SET_LIGHT_FIELD_INTENSITY);
                fieldIntensity.setValue(colorRgbAndIntensity.get(3).toString());
                extraActionCommandData.getFields().add(fieldIntensity);
            }
            doExtraAction(userId, deviceId, null, extraActionCommandData, commandDestinationTypeEnum);
        } catch (GenericException e) {
            logger.error("An error occurred while switching the light: " + e.getMessage());
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("Device with id {} and brand Paolotti is now {} color.", device.getId(), colorRgbAndIntensity);
    }

    @Override
    public void doExtraAction(String userId, String deviceId, String roomId, ExtraActionCommandData extraActionCommandData, CommandDestinationTypeEnum commandDestinationTypeEnum) throws GenericException {
        logger.info("Trying to execute command {} on {}", extraActionCommandData, deviceId!=null? "device " + deviceId : "room" + roomId);
        Device device = getDevice(deviceId);
        // todo do validation based on commandDestinationTypeEnum
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch (commandDestinationTypeEnum) {
                case TO_DEVICE:
                    sendMqttCommand(MQTT_TOPIC_IN_COMMAND_DEVICE.replace("{thingId}", device.getThingId()), extraActionCommandData, deviceId, device.getThingId(), null, commandDestinationTypeEnum);
                    break;
                case TO_ROOM:
                    /* todo - idea:
                     *   send to device a list of thingId the device will checks if there is the own thing id and so do thing
                     *   when a device is added to a room send a message with the room id , so the device could list to some topic like command/room/{roomId}/light/effect/ in this case we have to save inside the device the room id also delete and so on*/
//                    sendMqttCommand(MQTT_TOPIC_IN_EFFECTS_DEVICE.replace("{thingId}",deviceThingId),extraActionCommandData,deviceId,deviceThingId,null);
            }
            logger.info("Command {} sent successfully to device with id {} and brand {}", extraActionCommandData, deviceId, DeviceBrandEnum.PAOLOTTI);
        } catch (GenericException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("set commandData {} to device with id {} and brand {}", extraActionCommandData.getName(), deviceId, DeviceBrandEnum.PAOLOTTI);

    }

    @Override
    public void updateDeviceStatusFromAckReceived(AckCommand ackCommand) throws ValidationException, DeviceNotExistsException {
        logger.info("updating status of device id {} by ack received {}", ackCommand.getThingId(), ackCommand);
        // validation
        logger.info("validation started");
        if (ackCommand.getCommandId() == null || !StringUtils.hasText(ackCommand.getCommandId())) {
            throw new ValidationException("commandId is null or empty. commandId =" + ackCommand.getCommandId());
        }
        if (ackCommand.getThingId() == null || !StringUtils.hasText(ackCommand.getThingId())) {
            throw new ValidationException("deviceId is null or empty. thinkId =" + ackCommand.getThingId());
        }
        if (ackCommand.getAck() == null) {
            throw new ValidationException("command ack is null");
        }
        if (ackCommand.getDeviceStatus() == null) {
            throw new ValidationException("deviceStatus is null");
        }
        logger.info("validation finished. all is ok.");
        // execution
        // update command status on db
        updateCommandStatusOnDb(ackCommand);
        // update device status on db
        updateDeviceStatus(ackCommand.getThingId(), ackCommand.getDeviceStatus());
        logger.info("status of device id {} correctly updated", ackCommand.getThingId());
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
            saveCommandInDb(command, deviceId, deviceThingId, roomId, commandDestinationTypeEnum);
            // todo save command on db - create entity
        } catch (JsonProcessingException | MqttException e) {
            e.printStackTrace();
            throw new GenericException("error occurred: " + e.getMessage());
        }
        logger.info("sent - mqtt commandId {} on topic {} with payload {} to {}", commandId, topic, payload, logDeviceRoom);
    }
}
