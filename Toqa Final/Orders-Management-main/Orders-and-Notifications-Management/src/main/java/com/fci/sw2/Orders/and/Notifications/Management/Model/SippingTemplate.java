package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SippingTemplate implements Template {

    private int ShippingID;
    private String Location;
    private int fees;

    @Override
    public String ToString() {
        String message = "Order with ShippingID = " + ShippingID + " is Shipped to Location " + Location
                + " and fees = " + fees;
        return message;

    }

}
