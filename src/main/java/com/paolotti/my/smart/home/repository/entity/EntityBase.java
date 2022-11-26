package com.paolotti.my.smart.home.repository.entity;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class EntityBase {
    ObjectId id;
    private LocalDateTime creationDate;
}
