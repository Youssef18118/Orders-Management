package com.fci.sw2.Orders.and.Notifications.Management.Common;

import java.util.List;
import java.util.Map;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Category;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Notification;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;

interface Db {
    List<Customer> getCustomers();

    List<Product> getProducts();

    List<Order> getOrders();

    List<String> getCategories();

    List<Notification> getNotifications();

    Map.Entry<String, Integer> FindMostNotifedEmail();

    Map.Entry<String, Integer> FindMostNotifedPhoneNums();

    Map.Entry<String, Integer> FindMostSentTemplate();
}
