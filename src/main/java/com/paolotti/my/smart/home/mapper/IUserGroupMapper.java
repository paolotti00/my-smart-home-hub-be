package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.UserBaseGroup;
import com.paolotti.my.smart.home.repository.entity.GroupUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public interface IUserGroupMapper extends IBaseMapper{
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    GroupUserEntity toEntity(UserBaseGroup userGroup);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<GroupUserEntity> toEntities (ArrayList<UserBaseGroup> userGroupList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    UserBaseGroup toModel (GroupUserEntity groupUserEntity);
    ArrayList<UserBaseGroup> toModels (ArrayList<GroupUserEntity> groupUserEntityList);
}
