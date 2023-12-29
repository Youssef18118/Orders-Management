package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import org.springframework.stereotype.Service;

@Service
public class CustomerBsl {
    private final DbLists dbLists;

    public CustomerBsl(DbLists dbLists) {
        this.dbLists = dbLists;
    }

    // public String createAccount(Customer customer){
    // if(dbLists.existsCustomerById(customer.getCustomerId())){
    // return "This ID Already Exists ):";
    // }
    // dbLists.Customers.add(customer);
    // return "Created Successfully (:";

    // }

    // public String login(Login login){
    // if(dbLists.existCustomer(login)){
    // return "Login Successfully (:";

    // }
    // else{
    // return "Not Found";

    // }

    // }

}
