package com.paolotti.my.smart.home.model.deprecated;

@Deprecated
public class DeviceComponentSensorTemperature extends DeviceComponent {
    private Double temp;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
