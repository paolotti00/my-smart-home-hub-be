package com.paolotti.my.smart.home.rest.dto;

public class NetworkDataDto {
    String ip;
    String macAddress;
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

    @Override
    public String toString() {
        return "NetworkDataDto{" +
                "ip='" + ip + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
