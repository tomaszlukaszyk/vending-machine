package com.codecool;

import java.math.BigDecimal;
import java.util.Objects;

public class Product implements Comparable<Product> {

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
