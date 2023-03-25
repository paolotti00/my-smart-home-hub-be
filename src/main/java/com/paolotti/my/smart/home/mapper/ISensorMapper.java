package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.rest.SensorDto;
import com.paolotti.my.smart.home.model.Sensor;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class})
public interface ISensorMapper {
    SensorDto toDto(Sensor model);

    Sensor toModel(SensorDto dto);

    Sensor toModel(DeviceEntity.Sensor entity);

    DeviceEntity.Sensor toEntity(Sensor model);

    List<SensorDto> toDtoList(List<Sensor> modelList);

    List<Sensor> toModelList(List<SensorDto> dtoList);

    List<DeviceEntity.Sensor> toEntityList(List<Sensor> modelList);
}
