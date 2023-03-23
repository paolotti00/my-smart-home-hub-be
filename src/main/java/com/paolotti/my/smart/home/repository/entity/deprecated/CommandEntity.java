package com.paolotti.my.smart.home.repository.entity.deprecated;

import com.paolotti.my.smart.home.enums.deprecated.CommandDestinationTypeEnum;
import com.paolotti.my.smart.home.enums.deprecated.CommandStatusEnum;
import com.paolotti.my.smart.home.repository.entity.BaseEntity;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document(collection = "commands")
public class CommandEntity extends BaseEntity {
    private String commandId;
    private String deviceId;
    private String thingId;
    private CommandStatusEnum statusEnum;
    private CommandDestinationTypeEnum destinationType;
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
