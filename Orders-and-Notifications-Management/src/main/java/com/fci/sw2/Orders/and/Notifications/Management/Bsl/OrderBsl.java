package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import org.springframework.stereotype.Service;
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

@Service
public class OrderBsl {
    public static DbLists DB = new DbLists();
    public static NotificationBsl Nbsl = new NotificationBsl();

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
        simple.setCreatedTime(Instant.now());
        double price = simple.CalculateTotalPrice();
        simple.setTotalPrice(price);

        if (price <= customer.getBalance()) {
            customer.setBalance(customer.getBalance() - price);
            for (Customer temp : DB.Customers) {
                if (temp.getCustomerId() == customer.getCustomerId()) {
                    temp.setBalance(customer.getBalance());
                    // temp.setOrder(simple);
                }
            }
        } else { // price > customer.getBalance()
            System.out.println("Balance Not sufficient for placing Order");
            return null; // should be here
        }
        if (simple == null) {
            System.out.println("NUlllllllllllllllllllllllllllllll");
        }

        if (customer.getOrder() == null) {
            List<Order> Os = new ArrayList<>();
            customer.setOrder(Os);
        }
        customer.AddOrder(simple); // should be here to link customer with his order

        DB.Orders.add(simple); // adding to Orders In DB

        Nbsl.CreateNotification(simple);
        return simple;
    }

    public CompoundOrder placeCompoundOrder(@RequestBody CompundRequestFactory req) {
        Customer customer = new Customer(); //
        List<String> cities = new ArrayList<String>();
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
        simple.setCreatedTime(Instant.now());
        double price = simple.CalculateTotalPrice();
        simple.setTotalPrice(price);

        if (price <= customer.getBalance()) {
            customer.setBalance(customer.getBalance() - price);
            for (Customer temp : DB.Customers) {
                if (temp.getCustomerId() == customer.getCustomerId()) {
                    temp.setBalance(customer.getBalance());
                    cities.add(temp.getLocation()); // adding location to cities
                    // temp.setOrder(simple);
                }
            }
        } else { // price > customer.getBalance()
            System.out.println("Balance Not sufficient for placing Order");
            return null; // should be here
        }

        if (customer.getOrder() == null) {
            List<Order> Os = new ArrayList<Order>();
            customer.setOrder(Os);
        }
        customer.AddOrder(simple); // should be here to link customer with his order
        DB.Orders.add(simple); // should be here as this order should be avilable in DB

        // create Compound Order
        CompoundOrder compound = new CompoundOrder();
        compound.setType("Compound");

        List<Order> orders = new ArrayList<Order>();
        List<Order> OrdersDB = new ArrayList<Order>();

        orders.add(simple);

        // loop over Orders in Db
        List<Integer> orderIDs = req.getOrderIDs();

        if (orderIDs != null) {
            // System.out.println("!null");
            for (Order order : DB.Orders) {
                for (Integer orderID : orderIDs) {
                    if (orderID.equals(order.getOrderID())) { // check
                        OrdersDB.add(order);
                    }
                }
            }
        } else {
            System.out.println("Order IDs passed null");
        }

        for (Order Order : OrdersDB) {
            orders.add(Order);
        }

        for (Order O : orders) {
            for (Customer c : DB.Customers) {
                if (O.getCustomerID() == c.getCustomerId()) {
                    cities.add(c.getLocation()); // adding simple order locations to cities
                }
            }
        }

        if (!areAllElementsEqual(cities)) { // if thery are not in the same geographical region, return null
            System.out.println("Customers in Compound Order should be in the same geographical region");
            return null;
        }

        compound.setOrderes(orders);
        compound.setCustomerID(req.getCustomerID());

        compound.setCreatedTime(Instant.now());

        customer.AddOrder(compound); // seems wrong
        DB.Orders.add(compound); // should be after that

        // Balance is already decremented for simple Order

        compound.setTotalPrice(0.0);
        // set TotalPrice of Compound
        for (Order Order : compound.getOrderes()) {
            compound.setTotalPrice(compound.getTotalPrice() + Order.getTotalPrice());
        }

        Nbsl.CreateNotification(simple);
        Nbsl.CreateNotification(compound);
        return compound;

    }

    public String ShipOrder(Integer orderid, String Location) {
        Order temp = null;
        Customer customer = new Customer();
        for (Order Order : DB.Orders) {
            if (Order.getOrderID() == orderid) {
                temp = Order;
                break;
            }

        }

        if (temp == null) { // if not found
            return "Order not found!!!!";
        }

        if (temp.getShipping() != null) {
            return "Already shipped!!";
        }

        for (Customer c : DB.Customers) { // get corrosponding Customer
            if (temp.getCustomerID() == c.getCustomerId()) {
                customer = c;
                break;
            }
        }

        Shipping ship = new Shipping();
        ship.setShipLocation(Location);

        // want to check for location first
        if (temp.getType() == "Simple") {
            if (Location.equals(customer.getLocation())) { // if registered Location is the same as Shipping location

                Order order = null;
                for (Order O : customer.getOrder()) { // set Shipping in Customer class
                    if (O.getOrderID() == orderid) {
                        O.setShipping(ship); // set shipping
                        O.setTotalPrice(O.getTotalPrice() + 100); // adding shipping price to total price
                        order = O;
                        break;
                    }
                }
                customer.setBalance(customer.getBalance() - 100); // updating Balance

                for (Customer c : DB.Customers) {
                    if (customer.getCustomerId() == c.getCustomerId()) {
                        c.setBalance(customer.getBalance());

                        Iterator<Order> iterator = c.getOrder().iterator();
                        while (iterator.hasNext()) {
                            Order O = iterator.next();
                            if (O.getOrderID() == orderid) {
                                iterator.remove(); // Use iterator to remove the element
                            }
                        }

                        c.AddOrder(order); // Add Order AGAIN
                        break;
                    }
                }

                Nbsl.CreateNotification(temp);

                return "Simple Shippig done"; // not yet
            } else {
                return "You cant Ship outside your Location Zone";
            }
        } else { // compound Order
            List<Order> orders = ((CompoundOrder) temp).getOrderes();
            List<String> cities = new ArrayList<String>();
            for (Order order : orders) {
                for (Customer c : DB.Customers) {
                    if (order.getCustomerID() == c.getCustomerId()) { // get corrosponding customerin DB
                        cities.add(c.getLocation());// adding locations of Customers who are in compund in order in List
                        break;
                    }
                }

            }

            if (!areAllElementsEqual(cities) || !Location.equals(cities.get(0))) {// if who are in compund order are not
                                                                                  // in the same location
                                                                                  // or Shipping Location doesn't match
                                                                                  // their registered Location

                return "Shipping Not done!!";
            }

            // only set shipping to customer who made compund Ordee

            for (Order order : orders) {
                for (Customer c : DB.Customers) {
                    if (order.getCustomerID() == c.getCustomerId()) { // get corrosponding customerin DB
                        c.setBalance(c.getBalance() - (100 / orders.size())); // updating Balance

                        for (Order O : c.getOrder()) { // Set Shipping of who made compund Order (maybe not good
                                                       // practice)
                            if (O.getType().equals("Compound")) {
                                O.setShipping(ship);
                                O.setTotalPrice(O.getTotalPrice() + 100);

                            }
                        }

                        break;
                    }
                }

            }

        }

        Nbsl.CreateNotification(temp);
        System.out.println("CreateNotification called");

        return "Compound Shipping Done";

    }

    public Order ListOrderDetails(Integer orderID) {
        for (Order Order : DB.Orders) {
            if (Order.getOrderID() == orderID) {
                return Order;
            }
        }
        return null;
    }

    public String CancelShipOrder(Integer orderID) {
        Order temp = null;
        Customer customer = new Customer();
        for (Order Order : DB.Orders) {
            if (Order.getOrderID() == orderID) {
                temp = Order;
                break;
            }

        }

        if (temp == null) { // if not found
            return "Order not found!!!!";
        }

        if (temp.getShipping() == null) {
            return "Already no Shipping";
        }

        Instant currentTime = Instant.now();
        Instant createdTime = temp.getCreatedTime();
        long timeDifferenceSeconds = Duration.between(createdTime, currentTime).getSeconds();

        if (timeDifferenceSeconds > 60) { // if diff time is bigger than 60 seconds
            return "Cannot cancel shipping order. Time limit (60 Seconds) exceeded!";
        }

        for (Customer c : DB.Customers) { // get corrosponding Customer
            if (temp.getCustomerID() == c.getCustomerId()) {
                customer = c;
                break;
            }
        }

        for (Order O : customer.getOrder()) {
            if (O.getOrderID() == orderID) {
                O.setShipping(null); // set shipping to null
                break;
            }
        }

        customer.setBalance(customer.getBalance() + 100); // incrementing Balance after cancelleing Shipping

        for (Customer c : DB.Customers) { // updating this customer Balance in DB
            if (customer.getCustomerId() == c.getCustomerId()) {
                c.setBalance(customer.getBalance());
            }
        }

        return "shipping of OrderID" + orderID + " has beeen canceled successfully";

    }

    public String CancelOrder(Integer orderID) {
        Order temp = null;
        Customer customer = new Customer();
        double Price = 0;
        for (Order Order : DB.Orders) {
            if (Order.getOrderID() == orderID) {
                temp = Order;
                break;
            }

        }

        if (temp == null) { // if Order not found
            return "Order not found!!!!";
        }

        Instant currentTime = Instant.now();
        Instant createdTime = temp.getCreatedTime();
        long timeDifferenceSeconds = Duration.between(createdTime, currentTime).getSeconds();

        if (timeDifferenceSeconds > 30) { // if diff time is bigger than 30 seconds
            return "Cannot cancel order. Time limit (30 Seconds) exceeded!";
        }

        for (Customer c : DB.Customers) { // get corrosponding Customer
            if (temp.getCustomerID() == c.getCustomerId()) {
                customer = c;
                break;
            }
        }

        DB.Orders.remove(temp); // remove Order from DB

        for (Order O : customer.getOrder()) {
            if (O.getOrderID() == orderID) {
                Price = O.getTotalPrice(); // getting price from Order before cancelling
                customer.getOrder().remove(O); // remove Order from list of customers orders
                break;
            }
        }

        customer.setBalance(customer.getBalance() + Price); // incrementing Balance after cancelleing Order

        for (Customer c : DB.Customers) { // updating this customer Balance in DB
            if (customer.getCustomerId() == c.getCustomerId()) {
                c.setBalance(customer.getBalance());
            }
        }

        return "OrderID" + orderID + " has beeen canceled successfully";

    }

    public boolean areAllElementsEqual(List<String> list) {
        if (list == null || list.isEmpty()) {
            // Handle the case of an empty or null list according to your requirements.
            return false;
        }

        // Get the first element as the reference value
        String referenceValue = list.get(0);

        // Compare each element with the reference value
        for (String element : list) {
            if (!referenceValue.equals(element)) {
                return false;
            }
        }

        return true;
    }

}
