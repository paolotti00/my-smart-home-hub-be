package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.mqtt.CommandDto;
import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {IBaseMapper.class, IExtraActionCommandDataMapper.class})
public interface ICommandMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Command toModel(CommandEntity entity);

    Command toModel(CommandDto dto);

    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    CommandEntity toEntity(Command model);

    CommandDto toDto(Command model);
}
