package com.paolotti.my.smart.home.model;

import java.time.LocalDateTime;

public class BaseModel {
    private String id;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public BaseModel() {
    }

    public BaseModel(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

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

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
