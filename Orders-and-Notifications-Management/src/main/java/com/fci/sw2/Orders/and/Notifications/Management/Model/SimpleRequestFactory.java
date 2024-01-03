package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SimpleRequestFactory {

    @JsonProperty("CustomerID")
    private Integer CustomerID;

    @JsonProperty("ProductsSerialNo")
    private List<Integer> ProductsSerialNo;

    public Integer getCustomerID() {
        return CustomerID;
    }

    public List<Integer> getProductsSerialNo() {
        return ProductsSerialNo;
    }

}
