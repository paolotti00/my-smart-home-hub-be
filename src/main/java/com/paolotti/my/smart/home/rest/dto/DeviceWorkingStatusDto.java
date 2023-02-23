package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import lombok.ToString;

@ToString
public class DeviceWorkingStatusDto {

    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceWorkingStatusOut out;
    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceWorkingStatusIn in;
    @JsonView(JsonViewConfig.LowDetail.class)
    private OnOffStatusEnum powerStatus;

    public DeviceWorkingStatusOut getOut() {
        return out;
    }

    public void setOut(DeviceWorkingStatusOut out) {
        this.out = out;
    }

    public DeviceWorkingStatusIn getIn() {
        return in;
    }

    public void setIn(DeviceWorkingStatusIn in) {
        this.in = in;
    }

    public OnOffStatusEnum getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(OnOffStatusEnum powerStatus) {
        this.powerStatus = powerStatus;
    }

    @ToString
    public static class DeviceWorkingStatusOut {
        @JsonView(JsonViewConfig.LowDetail.class)
        private int intensity;
        @JsonView(JsonViewConfig.LowDetail.class)
        private OnOffStatusEnum powerStatus;
        @JsonView(JsonViewConfig.LowDetail.class)
        private ColorRgb colorRgb;

        public int getIntensity() {
            return intensity;
        }

        public void setIntensity(int intensity) {
            this.intensity = intensity;
        }

        public OnOffStatusEnum getPowerStatus() {
            return powerStatus;
        }

        public void setPowerStatus(OnOffStatusEnum powerStatus) {
            this.powerStatus = powerStatus;
        }

        public ColorRgb getColorRgb() {
            return colorRgb;
        }

        public void setColorRgb(ColorRgb colorRgb) {
            this.colorRgb = colorRgb;
        }
    }

    @ToString
    public static class DeviceWorkingStatusIn {
        @JsonView(JsonViewConfig.LowDetail.class)
        private double temperature;

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }
}
