package com.codecool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProductDispenser {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {

        if (products.containsKey(product)) {
            return;
        }

        products.put(product, 0);
    }

    public Set<Product> getAvailableProducts() {
        return products.keySet();
    }

}
