package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.OrderBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
// Import added
import com.fci.sw2.Orders.and.Notifications.Management.Model.*; // Import updated

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderBsl orderBsl;

    @PostMapping("/placeSimpleOrder")
    public SimpleOrder placeSimpleOrder(@RequestBody SimpleRequestFactory req) {
        return orderBsl.placeSimpleOrder(req);
    }

    @PostMapping("/placeCompoundOrder")
    public CompoundOrder placeCompoundOrder(@RequestBody CompundRequestFactory req) {
        return orderBsl.placeCompoundOrder(req);
    }

    @PostMapping("/ShipOrder/{OrderID}/{Location}")
    public String ShipOrder(@PathVariable("OrderID") Integer Orderid, @PathVariable("Location") String Location) {
        return orderBsl.ShipOrder(Orderid, Location);
    }

    @GetMapping("/DisplayOrder/{OrderID}")
    public Order ListOrderDetails(@PathVariable("OrderID") Integer OrderID) {
        return orderBsl.ListOrderDetails(OrderID);
    }

    @DeleteMapping("/CancelShipping/{OrderID}")
    public String CancelShipOrder(@PathVariable("OrderID") Integer OrderID) {
        return orderBsl.CancelShipOrder(OrderID);
    }

    @DeleteMapping("/CancelOrder/{OrderID}")
    public String CancelOrder(@PathVariable("OrderID") Integer OrderID) {
        return orderBsl.CancelOrder(OrderID);

    }

}
