package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Order {
    private static Integer orderCounter = 0;
    protected Integer OrderID;
    protected double TotalPrice;
    protected Shipping shipping;
    protected String Type;
    protected Cart cart;
    protected Integer CustomerID;
    protected Instant createdTime;

    public Order() {
        this.OrderID = ++orderCounter;
    }

    public abstract double CalculateTotalPrice();

    public Integer getOrderID() {
        return OrderID;
    }

}
