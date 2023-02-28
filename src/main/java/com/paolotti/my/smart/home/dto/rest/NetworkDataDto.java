package com.paolotti.my.smart.home.dto.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import lombok.ToString;

@ToString
public class NetworkDataDto {
    @JsonView(JsonViewConfig.LowDetail.class)
    String ip;
    @JsonView(JsonViewConfig.LowDetail.class)
    String macAddress;
    @JsonView(JsonViewConfig.LowDetail.class)
    String name;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
