package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.mqtt.CommandAckDto;
import com.paolotti.my.smart.home.model.CommandAck;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class})
public interface ICommandAckMapper {
    CommandAck toModel(CommandAckDto commandAckDto);
}
