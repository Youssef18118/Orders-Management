package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;

public class SimpleOrder extends Order {
    public SimpleOrder() {
        OrderID++;
    }

    public double CalculateTotalPrice() {
        List<Product> products = cart.getProducts();

        for (int i = 0; i < products.size(); i++) {
            TotalPrice += products.get(i).getPrice();
        }

        return TotalPrice;
    }

}
