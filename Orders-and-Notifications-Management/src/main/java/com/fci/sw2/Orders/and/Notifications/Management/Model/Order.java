package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Order {

    protected static int OrderID = 0;
    protected double TotalPrice;
    protected Shipping shipping;
    protected String Type;
    protected Cart cart;
    protected int CustomerID;

    public abstract double CalculateTotalPrice();

    public int getOrderID() {
        return OrderID;
    }

}
