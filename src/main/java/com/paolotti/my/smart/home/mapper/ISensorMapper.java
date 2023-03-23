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
    SensorDto toDto(Sensor sensor);

    Sensor toModel(SensorDto sensor);

    Sensor toModel(DeviceEntity.Sensor sensor);

    DeviceEntity.Sensor toEntity(Sensor sensor);

    List<SensorDto> toDtoList(List<Sensor> sensors);

    List<Sensor> toModelList(List<SensorDto> sensors);

    List<DeviceEntity.Sensor> toEntityList(List<Sensor> sensors);
}
