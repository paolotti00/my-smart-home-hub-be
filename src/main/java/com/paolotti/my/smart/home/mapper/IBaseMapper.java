package com.paolotti.my.smart.home.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface IBaseMapper {
    @Named("toObjectId")
    default ObjectId toObjectId(String id) {
        ObjectId toReturn = null;
        if(id!=null){
            toReturn = new ObjectId(id);
        }
        return toReturn;
    }

    ;

    @Named("toStringId")
    default String toStringId(ObjectId id) {
        return id.toHexString();
    }

    ;
}
