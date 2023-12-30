package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CompoundOrder extends Order {
    List<Order> orderes;

    public double CalculateTotalPrice() {

        for (Order ord : orderes) {
            TotalPrice += ord.getTotalPrice();
        }

        return TotalPrice;
    }

    // should be added
    public void addOrder(Order O) {
        orderes.add(O);
    }

    public void removeOrder(Order O) {
        orderes.remove(O);
    }

}
