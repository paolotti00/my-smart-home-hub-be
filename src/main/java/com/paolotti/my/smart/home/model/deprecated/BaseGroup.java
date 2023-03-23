package com.paolotti.my.smart.home.model.deprecated;

import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Deprecated
public class BaseGroup {
    private String id;
    private LocalDateTime creationDate;
    private String userOwnerId;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserOwnerId() {
        return userOwnerId;
    }

    public void setUserOwnerId(String userOwnerId) {
        this.userOwnerId = userOwnerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
