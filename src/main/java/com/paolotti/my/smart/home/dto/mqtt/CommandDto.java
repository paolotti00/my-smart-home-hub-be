package com.paolotti.my.smart.home.dto.mqtt;

import com.fasterxml.jackson.annotation.JsonRawValue;

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
}
