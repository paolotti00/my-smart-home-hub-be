package com.paolotti.my.smart.home.model;

public class BaseResponse {
    ResultStatusEnum resultStatus;
    String message;

    public ResultStatusEnum getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatusEnum resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
