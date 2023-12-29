package com.fci.sw2.Orders.and.Notifications.Management.Common;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Category;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.stereotype.Component;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Notification;

import java.util.*;

@Component
public class DbLists implements Db {
    public static List<Customer> Customers = new ArrayList<>();
    public static List<Order> Orders = new ArrayList<>();
    public static Map<String, List<Product>> Categories = new HashMap<>();
    public static Queue<Notification> NotificationQueue = new LinkedList<>();

    @Override
    public List<Customer> getCustomers() {
        return Customers;
    }

    @Override
    public List<Product> getProducts() {

        // Unified list to store all products
        List<Product> unifiedList = new ArrayList<>();

        // Loop over the map and add all products to the unified list
        for (List<Product> productList : Categories.values()) {
            unifiedList.addAll(productList);
        }

        return unifiedList;

    }

    @Override
    public List<Order> getOrders() {
        return Orders;
    }

    @Override
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();

        for (String catgName : Categories.keySet()) {
            categories.add(catgName);
        }

        return categories;
    }

    @Override
    public List<Notification> getNotifications() {
        List<Notification> notificationList = new ArrayList<>(NotificationQueue);

        return notificationList;

    }

}
