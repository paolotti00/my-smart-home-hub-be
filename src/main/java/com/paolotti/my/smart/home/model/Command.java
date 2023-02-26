package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;

import java.time.LocalDateTime;

public class Command extends BaseModel{
    private String commandId;
    private String deviceId;
    private String thingId;
    private CommandStatusEnum statusEnum;
    private CommandDestinationTypeEnum commandDestinationTypeEnum;
    private String data;

    public Command() {
    }

    public Command(LocalDateTime creationDate, String commandId, String deviceId, String thingId, CommandStatusEnum statusEnum, CommandDestinationTypeEnum commandDestinationTypeEnum, String data) {
        super(creationDate);
        this.commandId = commandId;
        this.deviceId = deviceId;
        this.thingId = thingId;
        this.statusEnum = statusEnum;
        this.commandDestinationTypeEnum = commandDestinationTypeEnum;
        this.data = data;
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

    public CommandDestinationTypeEnum getCommandDestinationType() {
        return commandDestinationTypeEnum;
    }

    public void setCommandDestinationType(CommandDestinationTypeEnum commandDestinationTypeEnum) {
        this.commandDestinationTypeEnum = commandDestinationTypeEnum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
