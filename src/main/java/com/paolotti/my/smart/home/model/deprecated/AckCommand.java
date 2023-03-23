package com.paolotti.my.smart.home.model.deprecated;

import com.paolotti.my.smart.home.enums.deprecated.ResultStatusEnum;
import lombok.ToString;

@ToString
public class AckCommand extends AckBase {
    private String commandId;
    private ResultStatusEnum ack;
    private DeviceStatus deviceStatus;

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
