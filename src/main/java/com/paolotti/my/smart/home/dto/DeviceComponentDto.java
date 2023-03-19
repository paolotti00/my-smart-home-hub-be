package com.paolotti.my.smart.home.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentLightDto;
import com.paolotti.my.smart.home.dto.rest.DeviceComponentSensorTemperatureDto;
import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.enums.DeviceConnectionStatusEnum;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import lombok.ToString;

@ToString
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeviceComponentLightDto.class, name = "LIGHT"),
        @JsonSubTypes.Type(value = DeviceComponentSensorTemperatureDto.class, name = "SENSOR_HEAT")
})
public class DeviceComponentDto {
    @JsonView(JsonViewConfig.HighDetail.class)
    private String id;
    @JsonView(JsonViewConfig.LowDetail.class)
    private DeviceComponentTypeEnum type;
    @JsonView(JsonViewConfig.LowDetail.class)
    private OnOffStatusEnum status;
    @JsonView(JsonViewConfig.LowDetail.class)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeviceComponentTypeEnum getType() {
        return type;
    }

    public void setType(DeviceComponentTypeEnum type) {
        this.type = type;
    }

    public OnOffStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OnOffStatusEnum status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
