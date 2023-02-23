package com.paolotti.my.smart.home.repository.entity;

import lombok.ToString;

@ToString
public class GroupBase extends EntityBase{
    private String name;
    private String userOwnerId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(String userOwnerId) {
        this.userOwnerId = userOwnerId;
    }
}
