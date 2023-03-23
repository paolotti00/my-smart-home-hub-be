package com.paolotti.my.smart.home.dto.deprecated.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import com.paolotti.my.smart.home.enums.deprecated.ResultStatusEnum;
import lombok.ToString;

@ToString
public class BaseResponseDto<T> {

    @JsonView(JsonViewConfig.LowDetail.class)
    private String errorCode;
    @JsonView(JsonViewConfig.LowDetail.class)
    private String message;
    @JsonView(JsonViewConfig.LowDetail.class)
    private ResultStatusEnum resultStatus;
    @JsonView(JsonViewConfig.LowDetail.class)
    private T data;

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

    public ResultStatusEnum getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatusEnum resultStatus) {
        this.resultStatus = resultStatus;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

