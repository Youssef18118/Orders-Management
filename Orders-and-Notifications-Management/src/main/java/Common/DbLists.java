package Common;

import Model.Customer;
import Model.Order;
import Model.Product;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.management.Notification;

public class DbLists {
    public List<Customer> Customers;
    public List<Product> Products;
    public List<Order> Orders;
    public Map<String, List<Order>> Categories;
    public Queue<Notification> NotificationQueue;

}
