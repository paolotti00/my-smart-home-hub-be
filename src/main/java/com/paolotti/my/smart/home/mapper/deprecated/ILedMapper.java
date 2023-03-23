package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.dto.deprecated.rest.LedDto;
import com.paolotti.my.smart.home.model.deprecated.Led;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.Map;

@Deprecated
@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ILedMapper {
   LedDto toDto (Led led);
   Led toModel (LedDto ledDto);
   Led toModel (DeviceEntity.LedEntity ledEntity);
   Map<Integer, DeviceEntity.LedEntity> toEntities (Map<Integer,Led> ledMap);
}
