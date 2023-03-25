package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import com.paolotti.my.smart.home.enums.CommandTypeEnum;

import java.time.LocalDateTime;

public class Command extends BaseModel {
    private String commandId;
    private String deviceId;
    private String thingId;
    private CommandStatusEnum statusEnum;
    private CommandDestinationTypeEnum destinationType;
    private CommandTypeEnum type;
    private String rawData;

    public Command() {
    }

    public Command(LocalDateTime creationDate, String commandId, String deviceId, String thingId, CommandStatusEnum statusEnum, CommandDestinationTypeEnum destinationType, String rawData) {
        super(creationDate);
        this.commandId = commandId;
        this.deviceId = deviceId;
        this.thingId = thingId;
        this.statusEnum = statusEnum;
        this.destinationType = destinationType;
        this.rawData = rawData;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getThingId() {
        return thingId;
    }

    public void setThingId(String thingId) {
        this.thingId = thingId;
    }

    public CommandStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(CommandStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public CommandDestinationTypeEnum getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(CommandDestinationTypeEnum destinationType) {
        this.destinationType = destinationType;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public CommandTypeEnum getType() {
        return type;
    }

    public void setType(CommandTypeEnum type) {
        this.type = type;
    }
}
