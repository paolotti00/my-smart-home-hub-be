package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import lombok.ToString;

@ToString
public class CommandAck {
    private String deviceId;
    private String commandId;
    private ResultStatusEnum ack;
    private DeviceStatus deviceStatus;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCommandId() {
        return commandId;
    }

    public void setCommandId(String commandId) {
        this.commandId = commandId;
    }

    public ResultStatusEnum getAck() {
        return ack;
    }

    public void setAck(ResultStatusEnum ack) {
        this.ack = ack;
    }

    public DeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
