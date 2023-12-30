package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SimpleRequestFactory {

    private int CustomerID;
    private List<Integer> ProductsSerialNo;

}
