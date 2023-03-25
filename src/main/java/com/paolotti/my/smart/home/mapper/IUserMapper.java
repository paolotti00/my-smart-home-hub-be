package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class})
public interface IUserMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    UserEntity toEntity(User model);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    User toModel(UserEntity entity);
}
