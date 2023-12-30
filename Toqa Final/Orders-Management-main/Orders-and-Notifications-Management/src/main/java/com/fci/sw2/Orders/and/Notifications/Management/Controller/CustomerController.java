package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.CustomerBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Customer;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Login;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")

public class CustomerController {
    // private Customer customer;
    @Autowired
    private CustomerBsl customerBsl;

    /*
     * public CustomerController(CustomerBsl customerBsl) {
     * this.customerBsl = customerBsl;
     * }
     */
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAccount(@RequestBody Customer customer) {
        String message = customerBsl.createAccount(customer);
        System.out.println(message);
        return ResponseEntity.ok(message);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        String message = customerBsl.login(login);
        System.out.println(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/Exit")
    public ResponseEntity<String> exits() {
        String result = customerBsl.exit();
        if (result.equals("Exit :)")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/AddToCart/{SerialNo}")
    public ResponseEntity<String> addToCart(@PathVariable("SerialNo") int SerialNo) {
        String result = customerBsl.AddToCart(SerialNo);
        if (result.equals("Added Product Successfully (:")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }

    }

    @GetMapping("/getCustomer")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerBsl.getCustomer());
    }

    @GetMapping("/h")
    public String get() {
        return "habelllll";
    }
}
