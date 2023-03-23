package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.deprecated.Command;
import com.paolotti.my.smart.home.dto.deprecated.mqtt.CommandDto;
import com.paolotti.my.smart.home.repository.entity.deprecated.CommandEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class, IDeviceComponentMapper.class})
public interface ICommandMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Command toModel (CommandEntity commandEntity);
    Command toModel (CommandDto commandDto);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    CommandEntity toEntity (Command command);
    CommandDto toDto (Command command);
}
