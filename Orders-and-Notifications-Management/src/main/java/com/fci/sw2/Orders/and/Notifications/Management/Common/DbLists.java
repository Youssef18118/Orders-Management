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
    public static List<Order> Orders;
    public static Map<String, List<Product>> Categories;
    public static Queue<Notification> NotificationQueue;

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
            for (Product P : productList) {
                P.setRemainingParts(productList.size() - 1);
            }
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

    // public boolean existsCustomerById(int id) {
    // if (Customers.isEmpty()) {
    // return false;
    // } else {
    // for (Customer customerDb : Customers) {
    // if (customerDb.getCustomerId() == id) {
    // return true;
    // }
    // }
    // return false;

    // }

    // }

    // public boolean existCustomer(Login login) {
    // if (login == null || login.getPassword() == null || login.getEmail() == null
    // || Customers == null) {
    // return false;
    // }

    // for (Customer customerDb : Customers) {
    // String dbPassword = customerDb.getPassword();
    // String dbEmail = customerDb.getEmail();

    // if (dbPassword != null && dbEmail != null &&
    // dbPassword.equals(login.getPassword())
    // && dbEmail.equals(login.getEmail())) {
    // return true;
    // }
    // }

    // return false;
    // }

    // public List<Product> listAvilableProduct() {
    // List<Product> avilableProducts = new ArrayList<>();
    // for (Product productDb : Products) {
    // if (productDb.getQuantum() != 0) {
    // avilableProducts.add(productDb);
    // }
    // }
    // return avilableProducts;

    // }

    // public List<Product> getProductsByCategory(String nameCategory) {
    // for (Map.Entry<String, List<Product>> entry : Categories.entrySet()) {
    // if (Objects.equals(entry.getKey(), nameCategory)) {
    // return entry.getValue();
    // }
    // }
    // return null;
    // }

}
