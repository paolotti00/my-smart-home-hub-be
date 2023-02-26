package com.paolotti.my.smart.home.repository.entity;

import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document(collection = "group_user")
public class UserGroupEntity extends GroupBaseEntity {
}
