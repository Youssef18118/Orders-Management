package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class OrderTemplate implements Template {
    private Integer CustomerID;
    private double Price;

    @Override
    public String ToString() {
        String message = "Order " + " is palced to Customer with ID " + CustomerID + " With total price " + Price;
        return message;

    }

}
