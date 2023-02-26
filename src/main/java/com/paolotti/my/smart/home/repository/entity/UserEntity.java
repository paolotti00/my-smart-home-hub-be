package com.paolotti.my.smart.home.repository.entity;

import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@ToString
@Document(collection = "users")
public class UserEntity extends BaseEntity {
    String name;
    String surname;
    String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
