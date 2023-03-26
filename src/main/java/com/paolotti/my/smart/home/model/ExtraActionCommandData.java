package com.paolotti.my.smart.home.model;

import com.paolotti.my.smart.home.enums.FieldTypeEnum;
import lombok.ToString;

import java.util.List;

@ToString
public class ExtraActionCommandData {
    private String name;
    private List<Field> fields;

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

    @ToString
    public static class Field{
        private String name;
        private FieldTypeEnum type;
        private boolean mandatory;
        private String value;

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

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}