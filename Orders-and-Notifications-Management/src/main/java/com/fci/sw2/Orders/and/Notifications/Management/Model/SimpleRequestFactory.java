package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SimpleRequestFactory implements RequestFactory {

    private int CustomerID;
    private Cart cart;

    // Create Order here by updating OrderID, Price, type, and making Shipping class
    // (we assme that shipping is always done when we make Order)
    @Override
    public Order CreateOrder() {

        return null; // remove it

    }

}
