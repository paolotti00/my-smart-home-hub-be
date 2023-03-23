package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.model.deprecated.EffectData;
import com.paolotti.my.smart.home.dto.deprecated.EffectDataDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IEffectDataMapper{
    EffectDataDto toDto (EffectData effectData);
    EffectData toModel(EffectDataDto effectDataDto);
}
