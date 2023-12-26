package com.fci.sw2.Orders.and.Notifications.Management.Common;

import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.stereotype.Component;

import java.util.*;

import javax.management.Notification;
@Component
public class DbLists {
    public  List<Customer> Customers = new ArrayList<>();
    public List<Product> Products;
    public List<Order> Orders;
    public Map<String, List<Product>> Categories;
    public Queue<Notification> NotificationQueue;



    public boolean existsCustomerById(int id){
        if(Customers.isEmpty()){
            return false;
        }
        else{
            for (Customer customerDb : Customers){
                if(customerDb.getCustomerId() == id){
                    return true;
                }
            }
            return false;

        }

    }

    public boolean  existCustomer(Login login) {
        if (login == null || login.getPassword() == null || login.getEmail() == null || Customers == null) {
            return false;
        }

        for (Customer customerDb : Customers) {
            String dbPassword = customerDb.getPassword();
            String dbEmail = customerDb.getEmail();

            if (dbPassword != null && dbEmail != null && dbPassword.equals(login.getPassword()) && dbEmail.equals(login.getEmail())) {
                return true;
            }
        }

        return false;
    }

    public List<Product> listAvilableProduct(){
        List<Product> avilableProducts = new ArrayList<>();
        for (Product productDb : Products){
            if(productDb.getQuantum() != 0){
                avilableProducts.add(productDb);
            }
        }
        return avilableProducts;

    }

    public List<Product> getProductsByCategory(String nameCategory){
        for (Map.Entry<String, List<Product>> entry :Categories.entrySet() ){
            if(Objects.equals(entry.getKey(), nameCategory)){
                return entry.getValue();
            }
        }
        return null;
    }


}
