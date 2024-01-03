package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompundRequestFactory {

    private int customerID;
    @JsonProperty("orderIDs")
    private List<Integer> OrderIDs = new ArrayList<>();
    @JsonProperty("productsSerialNo")
    private List<Integer> ProductsSerialNo = new ArrayList<>();

    public List<Integer> getOrderIDs() {
        return OrderIDs;
    }

    public List<Integer> getProductsSerialNo() {
        return ProductsSerialNo;
    }

}
