package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.DeviceComponentTypeEnum;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Map;

@ToString
public class DeviceComponentWrapper {
    private Map<Integer, DeviceComponentTypeEnum> numberOfComponents;
    private ArrayList<DeviceComponent> componentsList;

    public Map<Integer, DeviceComponentTypeEnum> getNumberOfComponents() {
        return numberOfComponents;
    }

    public void setNumberOfComponents(Map<Integer, DeviceComponentTypeEnum> numberOfComponents) {
        this.numberOfComponents = numberOfComponents;
    }

    public ArrayList<DeviceComponent> getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(ArrayList<DeviceComponent> componentsList) {
        this.componentsList = componentsList;
    }
}
