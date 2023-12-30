package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class OrderTemplate implements Template {
    private int CustomerID;
    private int OrderID;

    @Override
    public String ToString() {
        String message = "Order with ID " + OrderID + " is palced to Customer with ID " + CustomerID;
        return message;

    }

}
