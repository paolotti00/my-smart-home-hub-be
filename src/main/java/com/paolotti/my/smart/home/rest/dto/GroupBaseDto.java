package com.paolotti.my.smart.home.rest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.paolotti.my.smart.home.rest.dto.view.JsonViewConfig;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class GroupBaseDto {
    @JsonView(JsonViewConfig.HighDetail.class)
    private String id;
    @JsonView(JsonViewConfig.LowDetail.class)
    private LocalDateTime creationDate;
    @JsonView(JsonViewConfig.MediumDetail.class)
    private String userOwnerId;
    @JsonView(JsonViewConfig.LowDetail.class)
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
