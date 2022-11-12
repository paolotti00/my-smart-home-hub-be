package com.paolotti.my.smart.home.exception;

import static com.paolotti.my.smart.home.constant.MessageConst.X_FIELD_IS_MISSING;

public class MissingFieldException extends GenericException{
    public MissingFieldException(String fieldName) {
        super(fieldName + X_FIELD_IS_MISSING);
    }
}
