package com.paolotti.my.smart.home.exception;

public class GroupNotExistsException extends GenericException{
    public GroupNotExistsException(String groupId) {
        super("the group with id" +groupId +" not exist");
    }
}
