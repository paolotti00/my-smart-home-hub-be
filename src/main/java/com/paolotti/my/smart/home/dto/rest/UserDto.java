package com.paolotti.my.smart.home.dto.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.dto.rest.view.JsonViewConfig;
import lombok.ToString;

@ToString
public class UserDto {
    @JsonView(JsonViewConfig.MediumDetail.class)
    String id;
    @JsonView(JsonViewConfig.MediumDetail.class)
    String name;
    @JsonView(JsonViewConfig.MediumDetail.class)
    String surname;
    @JsonView(JsonViewConfig.LowDetail.class)
    String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
