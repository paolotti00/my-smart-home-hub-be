package com.paolotti.my.smart.home.mapper;

import com.paolotti.my.smart.home.utility.Utility;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.awt.*;

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

    @Named("toColorString")
    default String toColorString(Color rgbColor){
       return Utility.colorToString(rgbColor);
    }
    @Named("stringToColor")
    default Color stringToColor(String rgbColor){
        return Utility.parseColor(rgbColor);
    }

    ;
}
