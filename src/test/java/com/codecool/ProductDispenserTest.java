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
}