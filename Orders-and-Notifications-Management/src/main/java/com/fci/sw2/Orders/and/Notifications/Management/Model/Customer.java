package com.fci.sw2.Orders.and.Notifications.Management.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Customer {
    private int customerId;
    private String name;
    private String Email;
    private String Password;
    private double Balance;
    private String Location;
    private Cart cart;
    private Order order;

}
