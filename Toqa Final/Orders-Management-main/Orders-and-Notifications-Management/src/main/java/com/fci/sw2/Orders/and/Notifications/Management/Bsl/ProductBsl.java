package com.fci.sw2.Orders.and.Notifications.Management.Bsl;

import com.fci.sw2.Orders.and.Notifications.Management.Common.DbLists;
import com.fci.sw2.Orders.and.Notifications.Management.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductBsl {
    private final DbLists dbLists;

    public ProductBsl(DbLists dbLists) {
        this.dbLists = dbLists;
    }

    public String addProduct(Product product) {
        String nameCategory = product.getNameCategory();
        Map<String, List<Product>> categoryMap = dbLists.getCategoryMap();

        for (Product P : dbLists.getProducts()) {
            if (P.getSerialNo() == product.getSerialNo()) {
                return "Product with the same Serial No Already Exists";
            }
        }

        if (categoryMap == null) {
            categoryMap = new HashMap<>(); // Initialize the map if it's null
            dbLists.setCategoryMap(categoryMap); // Set the initialized map back to dbLists
        }

        if (!categoryMap.containsKey(nameCategory)) {
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            categoryMap.put(nameCategory, productList);
            return "Added Successfully :)";
        } else {
            categoryMap.get(nameCategory).add(product);
            return "Added Successfully :)";
        }

    }

    public String displayCategories() {
        StringBuilder categoriesString = new StringBuilder();

        if (dbLists.getCategories() == null || dbLists.getCategories().isEmpty()) {
            categoriesString.append("No categories found or the map is empty.");
            return categoriesString.toString();
        }

        for (Map.Entry<String, List<Product>> entry : dbLists.getCategoryMap().entrySet()) {
            String categoryName = entry.getKey();
            categoriesString.append("Category: ").append(categoryName).append("\n");
        }

        return categoriesString.toString();
    }

    public List<Product> ListAllProducts() {
        return dbLists.getProducts();
    }

    public String displayCountProducts() {
        StringBuilder result = new StringBuilder();

        if (dbLists.getCategoryMap().isEmpty()) {
            result.append("No categories found.");
        } else {
            for (Map.Entry<String, List<Product>> entry : dbLists.getCategoryMap().entrySet()) {
                String categoryName = entry.getKey();
                List<Product> productList = entry.getValue();
                int productCount = productList.size();

                result.append(categoryName).append("-> CountProducts: ").append(productCount).append("\n");
            }
        }

        return result.toString();
    }

    public String removeProduct(int productSerialNo) {
        if (dbLists.getCategoryMap() != null) {
            dbLists.getCategoryMap().values()
                    .forEach(productList -> productList.removeIf(product -> product.getSerialNo() == productSerialNo));
            return "Product with ID " + productSerialNo + " removed successfully.";
        } else {
            return "Categories map is null.";
        }
    }

}
