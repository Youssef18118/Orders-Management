package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBsl {
    private final DbLists dbLists;

    public ProductBsl(DbLists dbLists) {
        this.dbLists = dbLists;
    }

    // public List<Product> listAvilableProduct(){
    // return dbLists.listAvilableProduct();
    // }

    // public List<Product> getProductsByCategory(String nameCategory){
    // return dbLists.getProductsByCategory(nameCategory);
    // }

}
