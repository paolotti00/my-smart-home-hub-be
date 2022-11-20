package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.UserGroup;
import com.paolotti.my.smart.home.repository.entity.UserGroupEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IUserGroupMapper {
    UserGroupEntity toEntity(UserGroup userGroup);
    ArrayList<UserGroupEntity> toEntities (ArrayList<UserGroup> userGroupList);
    Device toModel (UserGroupEntity userGroupEntity);
    ArrayList<UserGroup> toModels (ArrayList<UserGroupEntity> userGroupEntityList);
}
