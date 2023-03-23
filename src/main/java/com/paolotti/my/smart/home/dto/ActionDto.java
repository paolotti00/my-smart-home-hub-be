package com.paolotti.my.smart.home.dto;

import com.paolotti.my.smart.home.enums.FieldTypeEnum;
import com.paolotti.my.smart.home.model.Action;
import lombok.ToString;

import java.util.List;

@ToString
public class ActionDto {
    private String name;
    private List<Action.Field> fields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action.Field> getFields() {
        return fields;
    }

    public void setFields(List<Action.Field> fields) {
        this.fields = fields;
    }

    @ToString
    public static class Field{
        private String name;
        private FieldTypeEnum type;
        private boolean mandatory;

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
    }
}
