package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.dto.mqtt.CommandDto;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface ICommandMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Command toModel (CommandEntity commandEntity);
    Command toModel (CommandDto commandDto);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    CommandEntity toEntity (Command command);
    CommandDto toDto (Command command);
}
