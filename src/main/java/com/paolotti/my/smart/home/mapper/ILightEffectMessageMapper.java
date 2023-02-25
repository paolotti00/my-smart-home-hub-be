package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.LightEffectMessage;
import com.paolotti.my.smart.home.rest.dto.mqtt.LightEffectMessageDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ILightEffectMessageMapper {
    LightEffectMessageDto toDto (LightEffectMessage lightEffectMessage);
    LightEffectMessage toModel(LightEffectMessageDto lightEffectMessageDto);
}
