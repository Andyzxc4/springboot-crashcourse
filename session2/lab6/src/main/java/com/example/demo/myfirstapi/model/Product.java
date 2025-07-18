package com.example.demo.myfirstapi.model;

import java.util.Objects; // Used for Objects.hash and Objects.equals

public class Product {

    private Long id;
    private String name;
    private double price;

    // Constructor to create new Product objects (without ID initially)
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Constructor to create Product objects with an ID (e.g., when loading from storage)
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters (we need a setter for ID as well, as it might be assigned by our "repository")
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Optional but Recommended: toString(), equals(), hashCode() for good practice
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}