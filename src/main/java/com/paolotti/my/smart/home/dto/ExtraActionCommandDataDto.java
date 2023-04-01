package com.paolotti.my.smart.home.dto;

import com.paolotti.my.smart.home.enums.FieldTypeEnum;
import com.paolotti.my.smart.home.enums.SensorTypeEnum;
import lombok.ToString;

import java.util.List;

@ToString
public class ExtraActionCommandDataDto {
    private String name;
    private List<Field> fields;
    private List<SensorTypeEnum> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<SensorTypeEnum> getCategories() {
        return categories;
    }

    public void setCategories(List<SensorTypeEnum> categories) {
        this.categories = categories;
    }

    @ToString
    public static class Field{
        private String name;
        private FieldTypeEnum type;
        private double min;
        private double max;
        private boolean mandatory;
        private Object value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public FieldTypeEnum getType() {
            return type;
        }

        public void setType(FieldTypeEnum type) {
            this.type = type;
        }

        public boolean isMandatory() {
            return mandatory;
        }

        public void setMandatory(boolean mandatory) {
            this.mandatory = mandatory;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
