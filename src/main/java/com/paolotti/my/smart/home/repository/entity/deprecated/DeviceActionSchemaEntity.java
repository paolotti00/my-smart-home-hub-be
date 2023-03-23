package com.paolotti.my.smart.home.repository.entity.deprecated;

import com.paolotti.my.smart.home.enums.deprecated.DeviceActionSchemaTypeEnum;
import com.paolotti.my.smart.home.repository.entity.BaseEntity;
import lombok.ToString;

@ToString
@Deprecated
public class DeviceActionSchemaEntity extends BaseEntity {
    private String name;
    private ColorRgb colorRgb;
    private Long durationMs;
    private int repetition;
    private DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColorRgb getColorRgb() {
        return colorRgb;
    }

    public void setColorRgb(ColorRgb colorRgb) {
        this.colorRgb = colorRgb;
    }

    public Long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Long durationMs) {
        this.durationMs = durationMs;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public DeviceActionSchemaTypeEnum getDeviceActionSchemaTypeEnum() {
        return deviceActionSchemaTypeEnum;
    }

    public void setDeviceActionSchemaTypeEnum(DeviceActionSchemaTypeEnum deviceActionSchemaTypeEnum) {
        this.deviceActionSchemaTypeEnum = deviceActionSchemaTypeEnum;
    }

    private static class ColorRgb{
        int r;
        int g;
        int b;

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public int getG() {
            return g;
        }

        public void setG(int g) {
            this.g = g;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }
    }
}
