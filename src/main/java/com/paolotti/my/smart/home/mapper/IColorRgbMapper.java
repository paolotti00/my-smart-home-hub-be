package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.ColorRgb;
import com.paolotti.my.smart.home.rest.dto.ColorRgbDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IColorRgbMapper {
    ColorRgbDto toDto(ColorRgb colorRgb);
    ColorRgb toModel(ColorRgbDto colorRgbDto);
}
