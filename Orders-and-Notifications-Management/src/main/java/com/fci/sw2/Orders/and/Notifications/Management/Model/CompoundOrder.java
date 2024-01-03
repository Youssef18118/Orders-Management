package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CompoundOrder extends Order {
    private List<Order> orderes = new ArrayList<>();

    public CompoundOrder() {
        super();

    }

    public double CalculateTotalPrice() {

        double sum = 0;
        for (Order ord : orderes) {
            sum += ord.CalculateTotalPrice();
        }

        return sum;
    }

    // should be added
    public void addOrder(Order O) {
        orderes.add(O);
    }

    public void removeOrder(Order O) {
        orderes.remove(O);
    }

}