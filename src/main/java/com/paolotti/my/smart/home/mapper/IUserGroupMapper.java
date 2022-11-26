package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.UserGroup;
import com.paolotti.my.smart.home.repository.entity.GroupUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IUserGroupMapper extends IBaseMapper{
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    GroupUserEntity toEntity(UserGroup userGroup);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<GroupUserEntity> toEntities (ArrayList<UserGroup> userGroupList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    UserGroup toModel (GroupUserEntity groupUserEntity);
    ArrayList<UserGroup> toModels (ArrayList<GroupUserEntity> groupUserEntityList);
}
