package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CompundRequestFactory {

    private int CustomerID;

    private List<Integer> OrderIDs;

    private List<Integer> ProductsSerialNo;

}
