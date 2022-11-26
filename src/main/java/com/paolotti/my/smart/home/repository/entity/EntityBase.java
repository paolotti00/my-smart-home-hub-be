package com.paolotti.my.smart.home.repository.entity;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class EntityBase {
    private ObjectId id;
    private LocalDateTime creationDate;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
