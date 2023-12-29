package com.fci.sw2.Orders.and.Notifications.Management.Controller;

import com.fci.sw2.Orders.and.Notifications.Management.Bsl.ProductBsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
   private ProductBsl productBsl;

   // @GetMapping("/getAvilableProduct")
   // public ResponseEntity<List> getAvilableProduct(){
   // return ResponseEntity.ok(productBsl.listAvilableProduct());
   // }

   // @GetMapping("/getByCategory/{nameCategory}")
   // public ResponseEntity<List> getByCategory(@PathVariable("nameCategory")
   // String nameCategory){
   // return ResponseEntity.ok(productBsl.getProductsByCategory(nameCategory));

   // }

}
