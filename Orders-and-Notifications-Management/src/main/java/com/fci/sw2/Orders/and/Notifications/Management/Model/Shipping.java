package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Shipping {

    private static Integer ShipID = 0;
    private String ShipLocation;

    public Shipping() {
        ShipID++;
    }

    public Integer getShipID() {
        return ShipID;
    }
}
