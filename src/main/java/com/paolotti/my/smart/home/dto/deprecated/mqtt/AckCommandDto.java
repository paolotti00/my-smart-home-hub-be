package com.paolotti.my.smart.home.dto.deprecated.mqtt;

import com.paolotti.my.smart.home.enums.deprecated.ResultStatusEnum;
import lombok.ToString;

@ToString
public class AckCommandDto extends AckBaseDto {
    private String commandId;
    private ResultStatusEnum ack;
    private DeviceStatusDto deviceStatus;

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

    public DeviceStatusDto getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(DeviceStatusDto deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
