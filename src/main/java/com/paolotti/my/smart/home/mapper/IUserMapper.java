package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.User;
import com.paolotti.my.smart.home.repository.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserEntity toEntity(User user);
    User toModel(UserEntity userEntity);
}
