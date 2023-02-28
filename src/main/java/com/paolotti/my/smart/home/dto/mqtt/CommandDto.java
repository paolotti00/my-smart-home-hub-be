package com.paolotti.my.smart.home.dto.mqtt;

import com.fasterxml.jackson.annotation.JsonRawValue;

public class CommandDto {
    String commandId;
    @JsonRawValue
    String data;

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
