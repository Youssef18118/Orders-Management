package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.ProductBsl;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
   private ProductBsl productBsl;

   @GetMapping("/RemoveProduct/{id}")
   public ResponseEntity<String> removeProduct(@PathVariable("id") int id) {
      String result = productBsl.removeProduct(id);
      if (result != null) {
         return ResponseEntity.ok(result);
      } else {
         return ResponseEntity.notFound().build();
      }
   }


   @PostMapping("/addProduct")
   public ResponseEntity<String> addProductEndpoint(@RequestBody Product product){
      String message = productBsl.addProduct(product);
      if (message != null) {
         return ResponseEntity.ok(message);
      } else {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/listAllProducts")
   public ResponseEntity<List<Product>> listAllProducts() {
      List<Product> productList = productBsl.ListAllProducts();
      if(productList != null){
         return ResponseEntity.ok(productList);
      }
      else {
         return ResponseEntity.notFound().build();

      }
   }

   @GetMapping("/displayCategories")
   public ResponseEntity<String> displayCategories() {
      String categories = productBsl.displayCategories();
      if (categories != null) {
         return ResponseEntity.ok(categories);
      } else {
         return ResponseEntity.notFound().build();
      }
   }


   @GetMapping("/displayCountProducts")
   public ResponseEntity<String> displayCountProducts(){
      String displayCountProducts = productBsl.displayCountProducts();
      if (displayCountProducts != null) {
         return ResponseEntity.ok(displayCountProducts);
      } else {
         return ResponseEntity.notFound().build();
      }


   }

}
