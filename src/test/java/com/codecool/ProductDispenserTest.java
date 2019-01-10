package com.codecool;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductDispenserTest {

    private ProductDispenser productDispenser = new ProductDispenser();

    @Test
    void testCanAddSingleProduct() {
        Product product = new Product("cola", 1f);
        productDispenser.addProduct(product);
        Set<Product> expected = new HashSet<>();
        expected.add(product);

        Set<Product> actual = productDispenser.getAvailableProducts();

        assertIterableEquals(expected, actual);
    }

    @Test
    void testCanAddMultipleProducts() {
        Set<Product> expected = new HashSet<>();
        expected.add(new Product("cola", 1f));
        expected.add(new Product("chips", 0.5f));
        expected.add(new Product("candy", 0.65f));
        expected.forEach(productDispenser::addProduct);

        Set<Product> actual = productDispenser.getAvailableProducts();

        assertIterableEquals(expected, actual);
    }

    @Test
    void testStockUpProduct() {
        Product product = new Product("cola", 1f);
        productDispenser.addProduct(product);
        productDispenser.stockUp(product, 1);

        assertTrue(productDispenser.isProductInStock(product));
    }
}