package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ShippingTemplate implements Template {

    private Integer ShippingID;
    private String Location;
    private Integer fees = 100;

    @Override
    public String ToString() {
        String message = "Order with ShippingID = " + ShippingID + " is Shipped to Location " + Location
                + " and fees = " + fees;
        return message;

    }

}
