package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Order {

    protected int OrderID;
    protected double TotalPrice;
    protected Shipping shipping;
    protected String Type;

    // public abstract double CalculateTotalPrice();

}
