package com.paolotti.my.smart.home.repository.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "group_user")
public class GroupUserEntity extends GroupBase{
}
