package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.rest.dto.ColorRgb;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IColorRgbMapper {
    ColorRgb toDto(com.paolotti.my.smart.home.model.ColorRgb colorRgb);
    com.paolotti.my.smart.home.model.ColorRgb toModel(ColorRgb colorRgb);
}
