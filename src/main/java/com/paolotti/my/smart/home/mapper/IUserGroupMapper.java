package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.Device;
import com.paolotti.my.smart.home.model.UserGroup;
import com.paolotti.my.smart.home.repository.entity.GroupUserEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IUserGroupMapper {
    GroupUserEntity toEntity(UserGroup userGroup);
    ArrayList<GroupUserEntity> toEntities (ArrayList<UserGroup> userGroupList);
    Device toModel (GroupUserEntity groupUserEntity);
    ArrayList<UserGroup> toModels (ArrayList<GroupUserEntity> groupUserEntityList);
}
