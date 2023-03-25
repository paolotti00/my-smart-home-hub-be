package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.ExtraActionCommandDataDto;
import com.paolotti.my.smart.home.model.ExtraActionCommandData;
import com.paolotti.my.smart.home.repository.entity.DeviceEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class})
public interface IExtraActionCommandDataMapper {
    ExtraActionCommandDataDto toDto(ExtraActionCommandData model);
    ExtraActionCommandData toModel(ExtraActionCommandDataDto dto);
    ExtraActionCommandData toModel(DeviceEntity.ExtraActionCommandData entity);
    DeviceEntity.ExtraActionCommandData toEntity(ExtraActionCommandData model);
    List<ExtraActionCommandDataDto> toDtoList(List<ExtraActionCommandData> modelList);
    List<ExtraActionCommandData> toModelList(List<ExtraActionCommandDataDto> dtoList);
    List<DeviceEntity.ExtraActionCommandData> toEntityList(List<ExtraActionCommandData> modelList);
}
