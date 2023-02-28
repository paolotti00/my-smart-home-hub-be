package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.EffectData;
import com.paolotti.my.smart.home.dto.EffectDataDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IEffectDataMapper{
    EffectDataDto toDto (EffectData effectData);
    EffectData toModel(EffectDataDto effectDataDto);
}
