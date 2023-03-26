package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.dto.rest.RoomDto;
import com.paolotti.my.smart.home.model.Room;
import com.paolotti.my.smart.home.repository.entity.RoomEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class, IUserMapper.class, IDeviceMapper.class})
public interface IRoomMapper {
    RoomDto toDto(Room room);

    Room toModel(RoomDto roomDto);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    Room toModel(RoomEntity roomEntity);

    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    RoomEntity toEntity(Room room);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<RoomDto> toDtoList(List<Room> deviceList);

    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    List<Room> toModelList(List<RoomEntity> roomEntityList);
}
