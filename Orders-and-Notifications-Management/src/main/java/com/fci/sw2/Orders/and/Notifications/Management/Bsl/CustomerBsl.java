package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Common.TempCustomer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Cart;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerBsl {
    private static DbLists dbLists = new DbLists();

    public CustomerBsl(DbLists dbLists) {
        this.dbLists = dbLists;
    }

    public boolean existsCustomerById(int id) {
        for (Customer customer : dbLists.getCustomers()) {
            if (customer.getCustomerId() == id) {
                return true; // Customer with the given ID exists
            }
        }
        return false; // Customer with the given ID doesn't exist
    }

    public String createAccount(Customer customer) {
        // System.out.println(customer.getCustomerId());
        // System.out.println(customer.getName());
        // System.out.println(customer.getPassword());
        // System.out.println(customer.getBalance());
        // System.out.println(customer.getEmail());
        // System.out.println(customer.getLocation());
        if (TempCustomer.getTempCustomer() != null) {
            return "Log out first and try again :(";

        } else {
            if (customer.getCustomerId() == null) {
                return "Customer ID cannot be null.";
            }

            if (existsCustomerById(customer.getCustomerId())) {
                return "This ID Already Exists :(";

            } else {
                dbLists.Customers.add(customer);
                return "Created Successfully :)";
            }
        }
    }

    public String login(Login signin) {
        Customer customer = getCustomerByLogin(signin);
        Customer tempCustomer = TempCustomer.getTempCustomer();
        if (customer == null) {
            System.out.print("Customer == null");
        }
        if (tempCustomer == null) {
            System.out.print("tempCustomer == null");

        }
        if (customer != null && tempCustomer == null) {
            TempCustomer.setTempCustomer(customer);
            return "Login Successfully (:";
        } else {
            return "Not Found";

        }

    }

    private Customer getCustomerByLogin(Login login) {
        List<Customer> customers = dbLists.Customers;
        Customer customer = null;

        if (customers != null && login != null && login.getEmail() != null && login.getPassword() != null) {
            for (Customer customerDb : customers) {
                String customerEmail = customerDb.getEmail();
                String customerPassword = customerDb.getPassword();

                if (customerEmail != null && customerPassword != null &&
                        customerEmail.equals(login.getEmail()) && customerPassword.equals(login.getPassword())) {
                    customer = customerDb;
                }
            }
        }

        return customer;
    }

    public String exit() {
        if (TempCustomer.getTempCustomer() != null) {
            TempCustomer.setTempCustomer(null);
            return " Exit :)";
        }
        return "Already Exit ):";
    }

    public String AddToCart(Integer SerialNo) {
        for (Product productDb : dbLists.getProducts()) {
            if (productDb.getSerialNo() == SerialNo) {

                Product product = productDb;

                if (TempCustomer.getTempCustomer().getCart() == null) {
                    Cart cart = new Cart();
                    List<Product> products = new ArrayList<>();
                    products.add(product);
                    cart.setProducts(products);
                    TempCustomer.getTempCustomer().setCart(cart);
                } else {
                    TempCustomer.getTempCustomer().getCart().getProducts().add(product);
                }

                return "Added Product Successfully (:";

            }
        }
        return "This product was not found ):";
    }

    public List<Customer> getCustomer() {
        return dbLists.Customers;
    }

}
