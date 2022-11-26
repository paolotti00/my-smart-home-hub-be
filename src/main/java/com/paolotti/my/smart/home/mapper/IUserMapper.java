package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper extends IBaseMapper{
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    UserEntity toEntity(User user);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    User toModel(UserEntity userEntity);
}
