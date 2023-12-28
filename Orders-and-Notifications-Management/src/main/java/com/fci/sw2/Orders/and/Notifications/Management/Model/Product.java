package com.fci.sw2.Orders.and.Notifications.Management.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Product {
    private int SerialNo;
    private String Name;
    private String Vendor;
    private double Price;
    private Category category;
    private int quantity;
    private int remainingParts;

}
