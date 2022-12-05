package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ToString
public class DeviceComponentsWrapperDto {
    @JsonView(JsonViewConfig.LowDetail.class)
    private Map<Integer, DeviceComponentTypeEnum> numberOfComponents;
    @JsonView(JsonViewConfig.HighDetail.class)
    private ArrayList<DeviceComponentDto> componentsList;

    public Map<Integer, DeviceComponentTypeEnum> getNumberOfComponents() {
        return numberOfComponents;
    }

    public void setNumberOfComponents(Map<Integer, DeviceComponentTypeEnum> numberOfComponents) {
        this.numberOfComponents = numberOfComponents;
    }

    public ArrayList<DeviceComponentDto> getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(ArrayList<DeviceComponentDto> componentsList) {
        this.componentsList = componentsList;
    }
}
