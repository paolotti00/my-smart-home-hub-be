package com.paolotti.my.smart.home.model;

import lombok.ToString;

import java.util.List;

@ToString // TODO instead of humidity, temperature use a list of measurament : {"type":"TEMPERATURE","value":12,"unit":"°"}
public class Room extends BaseModel {
    private List<String> usersOwnersIds;
    private String name;
    private double humidity;
    private double temp;
    private boolean haveLights;

    public List<String> getUsersOwnersIds() {
        return usersOwnersIds;
    }

    public void setUsersOwnersIds(List<String> usersOwnersIds) {
        this.usersOwnersIds = usersOwnersIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public boolean isHaveLights() {
        return haveLights;
    }

    public void setHaveLights(boolean haveLights) {
        this.haveLights = haveLights;
    }
}
