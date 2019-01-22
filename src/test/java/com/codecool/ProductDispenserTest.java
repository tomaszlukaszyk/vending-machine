package com.codecool;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProductDispenserTest {

    private ProductDispenser productDispenser = new ProductDispenser();

    @Test
    void testCanAddSingleProduct() {
        Product product = new Product("cola", new BigDecimal("1.00"));
        productDispenser.addProduct(product);
        Set<Product> expected = new HashSet<>();
        expected.add(product);

        Set<Product> actual = productDispenser.getAvailableProducts();

        assertIterableEquals(expected, actual);
    }

    @Test
    void testCanAddMultipleProducts() {
        Set<Product> expected = new HashSet<>();
        expected.add(new Product("cola", new BigDecimal("1.00")));
        expected.add(new Product("chips", new BigDecimal("0.50")));
        expected.add(new Product("candy", new BigDecimal("0.65")));
        expected.forEach(productDispenser::addProduct);

        Set<Product> actual = productDispenser.getAvailableProducts();

        assertIterableEquals(expected, actual);
    }

    @Test
    void testStockUpProduct() {
        Product product = new Product("cola", new BigDecimal("1.00"));
        productDispenser.addProduct(product);
        productDispenser.stockUp(product, 1);

        assertTrue(productDispenser.isProductInStock(product));
    }

    @Test
    void testProductIsOutOfStockAfterDispense() {
        Product product = new Product("cola", new BigDecimal("1.00"));
        productDispenser.addProduct(product);
        productDispenser.stockUp(product, 1);

        productDispenser.dispenseProduct(product);

        assertFalse(productDispenser.isProductInStock(product));
    }
}