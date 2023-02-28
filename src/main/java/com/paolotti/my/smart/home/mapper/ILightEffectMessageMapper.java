package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Action;
import com.paolotti.my.smart.home.dto.ActionDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = IEffectDataMapper.class)
public interface ILightEffectMessageMapper {
    ActionDto toDto (Action action);
    Action toModel(ActionDto actionDto);
}
