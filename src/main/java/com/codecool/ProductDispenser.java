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

    public void stockUp(Product product, int amount) {

        if (!products.containsKey(product)) {
            return;
        }

        products.put(product, amount);
    }

    public boolean isProductInStock(Product product) {
        return products.get(product) > 0;
    }

    public void dispenseProduct(Product product) {
        int amount = products.get(product);

        if (amount == 0) {
            return;
        }

        products.put(product, --amount);
    }
}
