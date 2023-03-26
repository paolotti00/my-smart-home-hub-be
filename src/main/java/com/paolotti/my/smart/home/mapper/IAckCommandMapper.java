package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.mqtt.AckCommandDto;
import com.paolotti.my.smart.home.model.AckCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class, IDeviceStatusMapper.class})
public interface IAckCommandMapper {

    AckCommand toModel(AckCommandDto ackCommandDto);
}
