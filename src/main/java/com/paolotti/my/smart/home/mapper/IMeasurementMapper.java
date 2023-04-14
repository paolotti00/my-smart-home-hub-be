package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.MeasurementDto;
import com.paolotti.my.smart.home.model.Measurement;
import com.paolotti.my.smart.home.repository.entity.MeasurementEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class})
public interface IMeasurementMapper {
    MeasurementDto toDto(Measurement model);

    Measurement toModel(MeasurementDto dto);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Measurement toModel(MeasurementEntity entity);

    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    MeasurementEntity toEntity(Measurement model);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<MeasurementDto> toDtoList(List<Measurement> modelList);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<Measurement> toModelList(List<MeasurementEntity> EntityList);
}
