package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CompoundOrder extends Order {
    List<Order> orderes;

    // logic should change
    // public double CalculateTotalPrice() {
    // return 0.0;
    // }

    // // should be added
    // addOrder(Order O): Void
    // removeOrder(Order O): Void
    // GetChild(OrderID: int): Order

}
