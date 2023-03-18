package com.paolotti.my.smart.home.exception;

public class RoomNotExistsException extends GenericException{
    public RoomNotExistsException(String groupId) {
        super("the group with id" +groupId +" not exist");
    }
}
