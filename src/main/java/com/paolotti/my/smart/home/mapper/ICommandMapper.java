package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Command;
import com.paolotti.my.smart.home.mqtt.dto.CommandDto;
import com.paolotti.my.smart.home.repository.entity.CommandEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class,IDeviceMapper.class})
public interface ICommandMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Command toModel (CommandEntity commandEntity);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    CommandEntity toEntity (Command command);
    CommandDto toDto (Command command);
    Command toModel (CommandDto commandDto);
}
