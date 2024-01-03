package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;

public class SimpleOrder extends Order {
    public SimpleOrder() {
        super();

    }

    public double CalculateTotalPrice() {
        List<Product> products = cart.getProducts();

        double sum = 0;
        for (int i = 0; i < products.size(); i++) {
            sum += products.get(i).getPrice();
        }

        return sum;
    }

}
