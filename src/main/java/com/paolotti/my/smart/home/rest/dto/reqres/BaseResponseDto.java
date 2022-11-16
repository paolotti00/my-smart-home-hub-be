package com.paolotti.my.smart.home.rest.dto.reqres;

public class BaseResponseDto {
    ResultStatusEnum resultStatus;
    String message;
    public enum ResultStatusEnum{
        SUCCESS,
        FAILED
    }

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

    @Override
    public String toString() {
        return "BaseResponseDto{" +
                "resultStatus=" + resultStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
