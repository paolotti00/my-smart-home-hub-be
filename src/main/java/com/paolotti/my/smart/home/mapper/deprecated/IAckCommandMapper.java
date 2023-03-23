package com.paolotti.my.smart.home.mapper.deprecated;

import com.paolotti.my.smart.home.dto.deprecated.mqtt.AckCommandDto;
import com.paolotti.my.smart.home.mapper.IBaseMapper;
import com.paolotti.my.smart.home.model.deprecated.AckCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" , injectionStrategy = InjectionStrategy.CONSTRUCTOR,uses = {IBaseMapper.class, IAckCommandDeviceStatusMapper.class})
public interface IAckCommandMapper {

    AckCommand toModel(AckCommandDto ackCommandDto);
}
