package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Cart;
import com.fci.sw2.Orders.and.Notifications.Management.Model.CompoundOrder;
import com.fci.sw2.Orders.and.Notifications.Management.Model.CompundRequestFactory;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Shipping;
import com.fci.sw2.Orders.and.Notifications.Management.Model.SimpleOrder;
import com.fci.sw2.Orders.and.Notifications.Management.Model.SimpleRequestFactory;

public class OrderBsl {
    public static DbLists DB = new DbLists();

    // public void createAccount(String username, double initialBalance, String
    // address) {
    // Customer customer = new Customer(username, initialBalance, address);
    // DB.Customers.put(username, customer);
    // }

    public SimpleOrder placeSimpleOrder(@RequestBody SimpleRequestFactory req) {

        Customer customer = new Customer(); //
        // get customer from DB
        for (Customer temp : DB.Customers) {
            if (temp.getCustomerId() == req.getCustomerID()) {
                customer = temp;
            }
        }

        List<Product> Products = new ArrayList<Product>(); //

        List<Product> productsDB = DB.getProducts();
        List<Integer> ProductsSerinalNo = req.getProductsSerialNo();

        // Getting products of the same serial No from DB
        for (Product P : productsDB) {
            for (Integer Serial : ProductsSerinalNo) {
                if (Serial == P.getSerialNo()) {
                    Products.add(P);
                }
            }
        }

        Cart cart = new Cart();
        cart.setProducts(Products);

        SimpleOrder simple = new SimpleOrder();
        simple.setType("Simple");
        simple.setCart(cart);
        simple.setCustomerID(req.getCustomerID());
        double price = simple.CalculateTotalPrice();

        if (price > customer.getBalance()) {
            customer.setBalance(customer.getBalance() - price);
        }

        DB.Orders.add(simple); // adding to Orders In DB
        return simple;
    }

    public CompoundOrder placeCompoundOrder(@RequestBody CompundRequestFactory req) {
        Customer customer = new Customer(); //
        // get customer from DB
        for (Customer temp : DB.Customers) {
            if (temp.getCustomerId() == req.getCustomerID()) {
                customer = temp;
            }
        }

        List<Product> Products = new ArrayList<Product>(); //

        List<Product> productsDB = DB.getProducts();
        List<Integer> ProductsSerinalNo = req.getProductsSerialNo();

        // Getting products of the same serial No from DB
        for (Product P : productsDB) {
            for (Integer Serial : ProductsSerinalNo) {
                if (Serial == P.getSerialNo()) {
                    Products.add(P);
                }
            }
        }

        Cart cart = new Cart();
        cart.setProducts(Products);

        SimpleOrder simple = new SimpleOrder();
        simple.setType("Simple");
        simple.setCart(cart);
        simple.setCustomerID(req.getCustomerID());
        double price = simple.CalculateTotalPrice();

        if (price > customer.getBalance()) {
            customer.setBalance(customer.getBalance() - price);
        }

        // create Compound Order
        CompoundOrder compound = new CompoundOrder();
        compound.setType("Compound");

        List<Order> orders = new ArrayList<Order>();
        List<Order> OrdersDB = new ArrayList<Order>();

        orders.add(simple);

        // loop over Orders in Db
        for (Order Order : DB.Orders) {
            for (Integer OrderID : req.getOrderIDs()) {
                if (OrderID == Order.getOrderID()) {
                    OrdersDB.add(Order);
                }

            }

        }

        for (Order Order : OrdersDB) {
            orders.add(Order);
        }

        compound.setOrderes(orders);
        compound.setCustomerID(req.getCustomerID());
        compound.CalculateTotalPrice();

        DB.Orders.add(compound);

        // Balance is already decremented for simple Order

        // set TotalPrice of Compound
        for (Order Order : compound.getOrderes()) {
            compound.setTotalPrice(compound.getTotalPrice() + Order.getTotalPrice());
        }

        return compound;

    }
    // CompoundOrder compoundOrder = new CompoundOrder(compoundOrderId); // Create
    // an instance

    // // Set orders using the instance
    // ((CompoundOrder) compoundOrder).setOrders(orders);

    // List<Order> successOrders = new ArrayList<>();

    // // Deduct the order total from the balance of each customer
    // if (isSameAddressForAllCustomers(orders)) {
    // for (Order order : orders) {
    // if (order instanceof SimpleOrder) {
    // SimpleOrder simpleOrder = (SimpleOrder) order;
    // String customerName = simpleOrder.getCustomerName();
    // Customer customer = DB.Customers.get(customerName);

    // if (customer.getBalance() >= simpleOrder.calculateTotalPrice()) {
    // double newBalance = customer.getBalance() -
    // simpleOrder.calculateTotalPrice();
    // customer.setBalance(newBalance);
    // successOrders.add(simpleOrder);
    // } else {
    // System.out.println("Insufficient balance for Customer: " +
    // customer.getUsername());
    // }
    // }
    // }

    // // Set orders using the instance
    // ((CompoundOrder) compoundOrder).setOrders(successOrders);

    // // Add the instance to DB.compoundOrders
    // DB.Orders.add(compoundOrder);
    // } else {
    // System.out.println("Can't make compound order because of different
    // locations");
    // }
    // }

    // // should ship simple and compund Orders at the same time
    // public void shipOrder(int orderId) {
    // Order order = findSimpleOrder(orderId);
    // if (order != null) {
    // shipSimpleOrder(order);
    // } else {
    // System.out.println("Simple Order with ID " + orderId + " not found.");
    // }
    // }

    // public static List<Order> getSimpleOrders() {
    // List<Order> result = new ArrayList<>();

    // for (Order order : DB.Orders) {
    // if (order != null) {
    // result.add(order);
    // }
    // }

    // return result;
    // }

    // public static List<Order> getcompoundOrders() {
    // List<Order> result = new ArrayList<>();
    // for (Order order : DB.Orders) {
    // if (order != null) {
    // result.add(order);
    // }
    // }

    // return result;
    // }

    // // public String shipCompoundOrder(int compoundOrderId) {
    // // CompoundOrder compoundOrder = findCompoundOrder(compoundOrderId);
    // // if (compoundOrder != null) {
    // // shipCompoundOrder(compoundOrder);
    // // } else {
    // // return ("Compound Order with ID " + compoundOrderId + " not found.");
    // // }
    // // return null;
    // // }

    // private Order findSimpleOrder(int orderId) {
    // for (Order order : DB.simpleOrders) {
    // if (((SimpleOrder) order).getOrderId() == orderId) {
    // return order;
    // }
    // }
    // return null;
    // }

    // private CompoundOrder findCompoundOrder(int compoundOrderId) {
    // for (Order compoundOrder : DB.compoundOrders) {
    // if (((CompoundOrder) compoundOrder).getCompoundOrderId() == compoundOrderId)
    // {
    // return ((CompoundOrder) compoundOrder);
    // }
    // }
    // return null;
    // }

    // // private void shipSimpleOrder(Order order) {
    // // String customerUserName = ((SimpleOrder) order).getCustomerName();
    // // Customer customer = DB.customers.get(customerUserName);
    // // Shipping ship = new OrderShipping();
    // // if (ship.calculateFee(customer)) {
    // // System.out.println(
    // // "Simple Order with ID " + ((SimpleOrder) order).getOrderId() + " shipped.
    // // Shipping Fee deducted.");
    // // System.out.println("Customer " + customer.getUsername() + "'s new balance:
    // $"
    // // + customer.getBalance());

    // // } else {
    // // System.out
    // // .println("Insufficient balance to ship Simple Order with ID " +
    // // ((simpleOrder) order).getOrderId());
    // // }
    // // }

    // // public String shipCompoundOrder(CompoundOrder compoundOrder) {
    // // List<Order> orders = compoundOrder.getOrders();
    // // Shipping ship = new Shipping();

    // // double individualShipFee = ((Shipping) ship).getShipFee() / orders.size();

    // // for (Order order : orders) {
    // // String customerUserName = ((SimpleOrder) order).getCustomerName();
    // // Customer customer = DB.Customers.get(customerUserName);
    // // if (ship.calculateFee(customer)) {
    // // return "Shipping Order with ID " + ((SimpleOrder) order).getOrderId() +
    // // " in Compound Order with ID " + compoundOrder.getCompoundOrderId() +
    // // ". Customer " + customer.getUsername() + "'s new balance: $" +
    // // customer.getBalance();
    // // } else {
    // // return "Insufficient balance to ship Order with ID " + ((simpleOrder)
    // // order).getOrderId() +
    // // " in Compound Order with ID " + compoundOrder.getCompoundOrderId() +
    // // " for Customer: " + customer.getUsername();
    // // }
    // // }
    // // return ""; // Add a default return statement if needed
    // // }

    // private boolean isSameAddressForAllCustomers(List<Order> orders) {
    // // Assuming all orders are SimpleOrders for simplicity
    // if (orders.isEmpty()) {
    // return false; // No customers in the compound order
    // }
    // // String customerUserName =((simpleOrder) order).
    // String CustomerName = ((SimpleOrder) orders.get(0)).getCustomerName();
    // // DbLists DB;
    // Customer customer = DB.Customers.get(CustomerName);
    // String commonAddress = customer.getAddress();

    // for (Order order : orders) {
    // String orderCustomerName = ((SimpleOrder) order).getCustomerName();
    // Customer ordercustomer = DB.Customers.get(orderCustomerName);
    // if (!commonAddress.equals(ordercustomer.getAddress())) {
    // return false; // Found a different address
    // }
    // }

    // return true; // All customers have the same address
    // }

    // public String displaySimpleOrders() {
    // StringBuilder response = new StringBuilder();

    // if (getSimpleOrders().isEmpty()) {
    // response.append("No simple orders available.");
    // } else {
    // response.append("Simple Orders:\n");

    // for (Order order : getSimpleOrders()) {
    // SimpleOrder simpleOrder = (SimpleOrder) order;
    // String customerUserName = (simpleOrder).getCustomerName();
    // Customer customer = DB.Customers.get(customerUserName);

    // response.append("Order ID: ").append(simpleOrder.getOrderId()).append("\n");
    // response.append("Customer: ").append(customer.getUsername()).append("\n");
    // response.append("Customer Balance:
    // ").append(customer.getBalance()).append("\n");
    // response.append("Address: ").append(customer.getAddress()).append("\n");
    // response.append("Total Price:
    // ").append(simpleOrder.calculateTotalPrice()).append("\n");
    // response.append("---------------\n");
    // }
    // }

    // return response.toString();
    // }

    // public String displayCompoundOrders() {
    // StringBuilder response = new StringBuilder();

    // if (getcompoundOrders().isEmpty()) {
    // response.append("No compound orders available.");
    // } else {
    // response.append("Compound Orders:\n");

    // for (Order order : getcompoundOrders()) {
    // CompoundOrder compoundOrder = (CompoundOrder) order;
    // List<Order> orders = compoundOrder.getOrders();

    // response.append("Compound Order ID:
    // ").append(compoundOrder.getCompoundOrderId()).append("\n");

    // for (Order subOrder : orders) {
    // SimpleOrder simpleOrder = (SimpleOrder) subOrder;
    // String customerUserName = (simpleOrder).getCustomerName();
    // Customer customer = DB.Customers.get(customerUserName);

    // response.append(" Sub Order ID:
    // ").append(simpleOrder.getOrderId()).append("\n");
    // response.append(" Customer: ").append(customer.getUsername()).append("\n");
    // response.append(" Address: ").append(customer.getAddress()).append("\n");
    // response.append(" Total Price:
    // ").append(simpleOrder.calculateTotalPrice()).append("\n");
    // response.append(" ---------------\n");
    // }

    // // Check if all customers in the compound order have the same address
    // if (isSameAddressForAllCustomers(orders)) {
    // response.append(" All customers have the same address.\n");
    // } else {
    // response.append(" Customers have different addresses.\n");
    // }

    // response.append("---------------\n");
    // }
    // }

    // return response.toString();
    // }

}
