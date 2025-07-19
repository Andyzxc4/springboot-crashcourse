package com.example.demo.myfirstapi.repository;

import com.example.demo.myfirstapi.model.Product; // Import your Product class
import org.springframework.stereotype.Repository; // <-- Import this!

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong; // For generating unique IDs

@Repository // Marks this class as a Spring Repository component
public class ProductRepository {

    // Our in-memory "database"
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());
    // AtomicLong for thread-safe ID generation (simple incrementer)
    private final AtomicLong nextId = new AtomicLong(1);

    // Constructor to populate with initial dummy data
    public ProductRepository() {
        products.add(new Product(nextId.getAndIncrement(), "Laptop", 1200.00));
        products.add(new Product(nextId.getAndIncrement(), "Mouse", 25.50));
        products.add(new Product(nextId.getAndIncrement(), "Keyboard", 75.00));
        System.out.println("ProductRepository initialized with " + products.size() + " products.");
    }

    /**
     * Finds all products in the repository.
     * @return A list of all products.
     */
    public List<Product> findAll() {
        return new ArrayList<>(products); // Return a copy to prevent external modification
    }

    /**
     * Finds a product by its ID.
     * @param id The ID of the product to find.
     * @return An Optional containing the product if found, or empty if not found.
     */
    public Optional<Product> findById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    /**
     * Saves a product. If the product has no ID, it's considered new and a new ID is assigned.
     * If the product has an ID, it attempts to update an existing product.
     * @param product The product to save.
     * @return The saved or updated product.
     */
    public Product save(Product product) {
        if (product.getId() == null) {
            // New product: assign a new ID and add to list
            product.setId(nextId.getAndIncrement());
            products.add(product);
            System.out.println("Product saved (new): " + product);
            return product;
        } else {
            // Existing product: find and update
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId().equals(product.getId())) {
                    products.set(i, product); // Replace old product with updated one
                    System.out.println("Product updated: " + product);
                    return product;
                }
            }
            // If ID was provided but not found, you might want to throw an exception
            // For this lab, we'll just return the product, but it wasn't added/updated
            System.out.println("Product with ID " + product.getId() + " not found for update.");
            return product; // Or throw new IllegalArgumentException("Product not found for update");
        }
    }

    // (We will add delete later in Session 3)
}