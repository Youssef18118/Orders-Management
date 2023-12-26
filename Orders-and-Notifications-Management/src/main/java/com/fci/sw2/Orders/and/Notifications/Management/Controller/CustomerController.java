package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.CustomerBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/customer")

public class CustomerController {
    //private Customer customer;
    @Autowired
    private  CustomerBsl customerBsl;



   /* public CustomerController(CustomerBsl customerBsl) {
        this.customerBsl = customerBsl;
    }*/
    @PostMapping( "/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody Customer customer){
        String message = customerBsl.createAccount(customer);
        System.out.println(message);
        return ResponseEntity.ok(message);


    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
       String message = customerBsl.login(login);
        System.out.println(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping( "/h")
    public String get(){
        return "habelllll";
    }
}
