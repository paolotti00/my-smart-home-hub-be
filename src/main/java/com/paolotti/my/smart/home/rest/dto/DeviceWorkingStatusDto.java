package com.paolotti.my.smart.home.rest.dto;

import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import lombok.ToString;

@ToString
public class DeviceWorkingStatusDto {

    private DeviceWorkingStatusOut out;
    private DeviceWorkingStatusIn in;
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
        private int intensity;
        private OnOffStatusEnum powerStatus;
        private ColorRgbDto colorRgbDto;

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

        public ColorRgbDto getColorRgbDto() {
            return colorRgbDto;
        }

        public void setColorRgbDto(ColorRgbDto colorRgbDto) {
            this.colorRgbDto = colorRgbDto;
        }
    }

    @ToString
    public static class DeviceWorkingStatusIn {
        private double temperature;

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }
    }
}
