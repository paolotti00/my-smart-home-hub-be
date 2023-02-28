package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.rest.LedDto;
import com.paolotti.my.smart.home.model.Led;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ILedMapper {
   LedDto toDto (Led led);
   Led toModel (LedDto ledDto);
   Led toModel (DeviceEntity.Led led);
}
