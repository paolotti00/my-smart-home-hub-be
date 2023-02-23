package com.paolotti.my.smart.home.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper( componentModel = "spring")
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
        String toReturn = null;
        if(id!=null){
            toReturn = id.toHexString();
        }
        return toReturn;
    }

    ;
}
