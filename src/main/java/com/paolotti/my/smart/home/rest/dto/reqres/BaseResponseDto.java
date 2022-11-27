package com.paolotti.my.smart.home.rest.dto.reqres;

import com.paolotti.my.smart.home.enums.ResultStatusEnum;

public class BaseResponseDto {

    private String errorCode;
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

