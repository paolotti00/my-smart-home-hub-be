package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Action;
import com.paolotti.my.smart.home.mqtt.dto.ActionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ILightEffectMessageMapper {
    ActionDto toDto (Action action);
    Action toModel(ActionDto actionDto);
}
