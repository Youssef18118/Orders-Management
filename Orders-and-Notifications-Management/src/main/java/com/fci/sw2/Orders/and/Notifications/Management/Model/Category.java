package com.fci.sw2.Orders.and.Notifications.Management.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Category {
    @JsonProperty("CategoryID")
    private Integer CategoryID;

    @JsonProperty("name")
    private String name;

}
