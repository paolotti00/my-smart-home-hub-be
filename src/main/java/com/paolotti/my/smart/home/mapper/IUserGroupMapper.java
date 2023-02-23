package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.model.UserBaseGroup;
import com.paolotti.my.smart.home.repository.entity.UserGroupEntity;
import org.bson.types.ObjectId;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;

@Mapper( componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {IBaseMapper.class})
public interface IUserGroupMapper {
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    UserGroupEntity toEntity(UserBaseGroup userGroup);
    @Mapping(source = "id", target = "id", qualifiedByName = "toObjectId")
    ArrayList<UserGroupEntity> toEntities (ArrayList<UserBaseGroup> userGroupList);
    @Mapping(source = "id", target = "id", qualifiedByName = "toStringId")
    UserBaseGroup toModel (UserGroupEntity userGroupEntity);
    ArrayList<UserBaseGroup> toModels (ArrayList<UserGroupEntity> userGroupEntityList);
}
