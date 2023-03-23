package com.paolotti.my.smart.home.model.deprecated;

import lombok.ToString;

@ToString
public class ValidationHelperObject<T> {
    String fieldName;
    T value;
    T controlField;
    boolean valid;
    ValidationType validationType;

    public ValidationHelperObject(){};
    public ValidationHelperObject(String fieldName, T value,ValidationType validationType) {
        this.fieldName = fieldName;
        this.value = value;
        this.validationType = validationType;
    }
    public ValidationHelperObject(String fieldName, T value,ValidationType validationType, T controlField) {
        this.fieldName = fieldName;
        this.value = value;
        this.controlField = controlField;
        this.validationType = validationType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getControlField() {
        return controlField;
    }

    public void setControlField(T controlField) {
        this.controlField = controlField;
    }

    public ValidationType getValidationType() {
        return validationType;
    }

    public void setValidationType(ValidationType validationType) {
        this.validationType = validationType;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public enum ValidationType{
        NOT_NULL
    }
}
