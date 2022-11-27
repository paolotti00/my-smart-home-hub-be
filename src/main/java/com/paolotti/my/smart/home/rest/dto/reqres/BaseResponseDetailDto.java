package com.paolotti.my.smart.home.rest.dto.reqres;

import com.paolotti.my.smart.home.enums.ResultStatusEnum;

public class BaseResponseDetailDto {
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

    @Override
    public String toString() {
        return "BaseResponseDtoDetail{" +
                "resultStatus=" + resultStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
