package com.paolotti.my.smart.home.exception;

public class MissingFieldException extends GenericException{
    public MissingFieldException(String fieldName) {
        super(fieldName + " is missing");
    }
    public MissingFieldException(String fieldName,String fieldType) {
        super(fieldName +" is missing fieldType" + fieldType);
    }
}
