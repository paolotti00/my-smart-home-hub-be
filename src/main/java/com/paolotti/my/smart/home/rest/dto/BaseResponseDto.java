package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.enums.ResultStatusEnum;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import lombok.ToString;

@ToString
public class BaseResponseDto {

    @JsonView(JsonViewConfig.LowDetail.class)
    private String errorCode;
    @JsonView(JsonViewConfig.LowDetail.class)
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

