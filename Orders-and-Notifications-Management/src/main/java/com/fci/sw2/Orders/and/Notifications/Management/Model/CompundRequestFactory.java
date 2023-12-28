package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

public class CompundRequestFactory implements RequestFactory {

    private int CustomerID;

    private List<Integer> OrderIDs;

    // Go through Logic
    @Override
    public Order CreateOrder() {

        return null; // remove it
    }

}
