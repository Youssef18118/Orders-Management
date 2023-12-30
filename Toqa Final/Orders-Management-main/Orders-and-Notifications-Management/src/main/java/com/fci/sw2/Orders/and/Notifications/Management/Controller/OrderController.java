//package com.fci.sw2.Orders.and.Notifications.Management.Controller;
//
//import java.util.List;
//
//import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fci.sw2.Orders.and.Notifications.Management.Bsl.OrderBsl;
//import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
//import com.fci.sw2.Orders.and.Notifications.Management.Model.Order;
//
//@RestController
//@RequestMapping("/api/orders")
//public class OrderController {
//
//    private OrderBsl OBsl;
//
//    @Autowired
//    public OrderController(OrderBsl OBsl) {
//        this.OBsl = OBsl;
//    }
//
//    @PostMapping("/createAccount")
//    public void createAccount(
//            @RequestParam String username,
//            @RequestParam double initialBalance,
//            @RequestParam String address) {
//        // OBsl.createAccount(username, initialBalance, address);
//    }
//
//    @PostMapping("/placeSimpleOrder")
//    public void placeSimpleOrder(
//            @RequestParam int orderId,
//            @RequestParam String username,
//            @RequestBody List<Product> products) {
//        // OBsl.placeSimpleOrder(orderId, username, products);
//    }
//
//    // @PostMapping("/shipOrder")
//    // public void shipOrder(@RequestParam int orderId) {
//    // OBsl.shipOrder(orderId);
//    // }
//
//    // @GetMapping("/displaySimpleOrders")
//    // public String displaySimpleOrders() {
//    // return OBsl.displaySimpleOrders();
//    // }
//
//    @GetMapping("/shimaa")
//    public String habal() {
//        return "shimaa";
//    }
//
//    // @PostMapping("/placeCompoundOrder")
//    // public ResponseEntity<String> placeCompoundOrder(@RequestParam int
//    // CompoundOrderId,
//    // @RequestBody List<Order> compoundOrder) {
//    // try {
//    // OBsl.placeCompoundOrder(CompoundOrderId, compoundOrder);
//    // return ResponseEntity.ok("Compound order placed successfully.");
//    // } catch (Exception e) {
//    // e.printStackTrace(); // Log the exception using a proper logger
//    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//    // .body("Error placing compound order: " + e.getMessage());
//    // }
//    // }
//
//    // @PostMapping("/shipCompoundOrder")
//    // public ResponseEntity<String> shipCompoundOrder(@RequestParam int
//    // compoundOrderId, @RequestParam double shipFee) {
//    // try {
//    // // String result = OBsl.shipCompoundOrder(compoundOrderId);
//    // // return ResponseEntity.ok(result);
//    // } catch (Exception e) {
//    // e.printStackTrace(); // Log the exception using a proper logger
//    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//    // .body("Error shipping compound order: " + e.getMessage());
//    // }
//    // }
//
//    // @GetMapping("/displayCompoundOrders")
//    // public String displayCompoundOrders() {
//    // return OBsl.displayCompoundOrders();
//    // }
//    // Additional endpoints can be added as needed...
//
//}
