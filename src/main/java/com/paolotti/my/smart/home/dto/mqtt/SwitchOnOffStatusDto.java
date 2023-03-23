package com.paolotti.my.smart.home.dto.mqtt;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.paolotti.my.smart.home.enums.OnOffStatusEnum;
import lombok.ToString;

@ToString
public class SwitchOnOffStatusDto {
    OnOffStatusEnum status;

    public SwitchOnOffStatusDto() {
    }

    public SwitchOnOffStatusDto(OnOffStatusEnum status) {
        this.status = status;
    }

    public OnOffStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OnOffStatusEnum status) {
        this.status = status;
    }
}
