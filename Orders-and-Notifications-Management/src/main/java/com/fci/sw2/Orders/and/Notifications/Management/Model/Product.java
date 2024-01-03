package com.fci.sw2.Orders.and.Notifications.Management.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Product {
    @JsonProperty("SerialNo")
    private Integer SerialNo;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Vendor")
    private String Vendor;

    @JsonProperty("Price")
    private double Price;

    @JsonProperty("Category")
    private Category category;

    @JsonProperty("Quantity")
    private Integer quantity;

    // public Product() {
    // this.SerialNo = 0;
    // this.Name = null;
    // this.Vendor = null;
    // this.Price = 0.0;
    // this.category = new Category();
    // this.quantity = 0;
    // }

}
