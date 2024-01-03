package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Customer {

    private Integer customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
    private double balance;
    private String location;
    private Cart cart;
    private List<Order> order = new ArrayList<>();

    public Customer() {

    }

    public void AddOrder(Order O) {
        this.order.add(O);
    }

}
