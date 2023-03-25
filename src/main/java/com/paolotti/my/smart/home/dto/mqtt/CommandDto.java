package com.paolotti.my.smart.home.dto.mqtt;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.ToString;

@ToString
public class CommandDto {
    String commandId;
    @JsonRawValue
    String rawData;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }
}
