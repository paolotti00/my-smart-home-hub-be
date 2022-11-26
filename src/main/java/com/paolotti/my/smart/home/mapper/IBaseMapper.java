package com.paolotti.my.smart.home.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface IBaseMapper {
    @Named("toObjectId")
    default ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }

    ;

    @Named("toStringId")
    default String toStringId(ObjectId id) {
        return id.toHexString();
    }

    ;
}
