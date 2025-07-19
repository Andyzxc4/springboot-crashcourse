package com.example.demo.myfirstapi.controller;

import com.example.demo.myfirstapi.model.Product;
import com.example.demo.myfirstapi.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository; // Declare the dependency

    // Constructor Injection: Spring will automatically provide ProductRepository here
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "Hello! This is the home page for Lab 10 :)";
    }

    //  return a List of all products in our product repository
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")  // {id} is a path variable
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        //  Check if product was found
        // If found, return it with HTTP 200 OK
        // .get() gets the Product from Optional
        // If not found, return HTTP 404 Not Found
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
        The original createProduct method (now commented out) was provided in the lab.
        However, when tested in Postman, it returned a 500 Internal Server Error and failed to add the product.

        To resolve this, I refactored the method to accept a HashMap<String, Object> instead of a Product object.
        I then manually extracted and converted the values based on the expected types in the Product constructor.
        This approach returned a 201 Created status in Postman, and the product was successfully added.
    */

//    @PostMapping("/products") // Maps HTTP POST requests to /products
//    @ResponseStatus(HttpStatus.CREATED) // Optional: Returns 201 Created by default for POST success
//    public Product createProduct(@RequestBody Product newProduct) {
//        // newProduct object automatically populated from JSON in request body
//        return productRepository.save(newProduct); // Save and return the saved product (with its new ID)
//    }

    @PostMapping("/products") // Maps HTTP POST requests to /products
    @ResponseStatus(HttpStatus.CREATED) // Optional: Returns 201 Created by default for POST success
    public Product createProduct(@RequestBody HashMap<String, Object> productMap) {
        //  extract each attributes
        Object idObj = productMap.get("id");
        Long id = (idObj == null) ? null : ((Number) idObj).longValue();

        String name = (String) productMap.get("name");
        double price = Double.parseDouble(productMap.get("price").toString());

        Product newProduct = new Product(id, name, price);

        return productRepository.save(newProduct); // Save and return the saved product (with its new ID or Update existing Product)
    }

}
