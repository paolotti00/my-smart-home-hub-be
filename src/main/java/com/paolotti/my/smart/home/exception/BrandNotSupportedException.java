package com.paolotti.my.smart.home.exception;

import com.paolotti.my.smart.home.enums.DeviceBrandEnum;

public class BrandNotSupportedException extends GenericException{
    public BrandNotSupportedException(String message) {
        super(message);
    }
    public BrandNotSupportedException(DeviceBrandEnum brandEnum){
        super("the brand " + brandEnum + " is not supported for this operation");
    }
}
