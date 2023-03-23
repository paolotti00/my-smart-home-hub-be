package com.paolotti.my.smart.home.model.deprecated;

import com.paolotti.my.smart.home.enums.deprecated.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.deprecated.CommandStatusEnum;
import com.paolotti.my.smart.home.model.BaseModel;

import java.time.LocalDateTime;

public class Command extends BaseModel {
    private String commandId;
    private String deviceId;
    private String thingId;
    private CommandStatusEnum statusEnum;
    private CommandDestinationTypeEnum destinationType;
    private String data;

    public Command() {
    }

    public Command(LocalDateTime creationDate, String commandId, String deviceId, String thingId, CommandStatusEnum statusEnum, CommandDestinationTypeEnum destinationType, String data) {
        super(creationDate);
        this.commandId = commandId;
        this.deviceId = deviceId;
        this.thingId = thingId;
        this.statusEnum = statusEnum;
        this.destinationType = destinationType;
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

    public CommandDestinationTypeEnum getDestinationType() {
        return destinationType;
    }

    public void setDestinationType(CommandDestinationTypeEnum destinationType) {
        this.destinationType = destinationType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
