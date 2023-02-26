package com.paolotti.my.smart.home.repository.entity;

import com.paolotti.my.smart.home.enums.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.CommandStatusEnum;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document(collection = "commands")
public class CommandEntity extends BaseEntity {
    private String commandId;
    private String deviceId;
    private String thingId;
    private CommandStatusEnum statusEnum;
    private CommandDestinationTypeEnum commandDestinationTypeEnum;
    private String data;

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

    public CommandDestinationTypeEnum getCommandDestinationTypeEnum() {
        return commandDestinationTypeEnum;
    }

    public void setCommandDestinationTypeEnum(CommandDestinationTypeEnum commandDestinationTypeEnum) {
        this.commandDestinationTypeEnum = commandDestinationTypeEnum;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
