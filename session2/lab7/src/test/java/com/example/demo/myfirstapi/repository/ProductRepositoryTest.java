package com.example.demo.myfirstapi.repository;

import com.example.demo.myfirstapi.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
    }

    // findAll() tests
    @Test
    void testFindALlSize() {
        //  called findAll() method and determine its size
        List<Product> products = productRepository.findAll();

        // 3 is the expected Size in this case for Products ArrayList
        assertEquals(3, products.size());
    }

    @Test
    void testFindALl() {
        //  create a new initialized Product ArrayList to test as dummy data
        ArrayList<Product> expected = new ArrayList<>(Arrays.asList(
                new Product(1L, "Laptop", 1200.00),
                new Product(2L, "Mouse", 25.5),
                new Product(3L, "Keyboard", 75.0)
        ));

        assertEquals(expected, productRepository.findAll());
    }

    //  findById() tests
    @Test
    void testFindByIdFirstProduct() {
        Optional<Product> expected = Optional.of(new Product(1L, "Laptop", 1200.00));

        assertEquals(expected, productRepository.findById(1L));
    }

    @Test
    void testFindByIdSecondProduct() {
        Optional<Product> expected = Optional.of(new Product(2L, "Mouse", 25.5));

        assertEquals(expected, productRepository.findById(2L));
    }

    @Test
    void testFindByIdThirdProduct() {
        Optional<Product> expected = Optional.of(new Product(3L, "Keyboard", 75.0));

        assertEquals(expected, productRepository.findById(3L));
    }

    @Test
    void testFindByIdNonExistentProduct() {
        //  to test non-existent product, we can pass in ID 5, to check if "Optional.empty()" is the expected output
        assertEquals(Optional.empty(), productRepository.findById(5L));
    }

    //  save() tests
    @Test
    void testSaveToCheckUpdatedProduct() {
        //  declare a Product with existing ID in the DB, but different "name" and "price" atributes
        Product newProduct = new Product(2L, "Monitor", 90.43);

        /*
            The save() method should return the same product we passed in
            (newProduct -> with ID = 2). We also use an additional assertEquals to verify
            that the original attributes of the product with ID 2 remain unchanged.

            Note: Although it’s generally best practice to use only one assert
            per test, we include a second one here for confirmation.
        */
        assertEquals(Optional.of(new Product(2L, "Mouse", 25.5)), productRepository.findById(2L)); // before calling save()
        assertEquals(newProduct, productRepository.save(newProduct)); // after calling save()
    }

    @Test
    void testSaveToCheckNonExistentIdOfNewProduct() {
        //  declare a new Product with no ID
        Product newProduct = new Product(7L, "Test", 7.25);

        productRepository.save(newProduct);

        /*
            The expected size of the products list remains 3 because we're trying to save a product
            with a non-existent ID. The save() method is designed to only:
            - Add a product if it has no ID, or
            - Update an existing product if the ID already exists.

            Since this product has an ID that doesn't match any existing product,
            it is not added to the list. Although the method returns the product,
            it is not actually saved in the backend (products list).
        */
        int expected = 3;

        assertEquals(expected, productRepository.findAll().size());
    }

    @Test
    void testSaveToCheckNewProductWithNoProvidedId() {
        //  declare a new Product with no ID
        Product newProduct = new Product("Test", 7.25);

        productRepository.save(newProduct);

        // used products.get(products.size() - 1) to retrieve the most recently added product after calling the save() method
        List<Product> products = productRepository.findAll();
        Product latestProduct = products.get(products.size() - 1);

        // the expected ID is 4, since the last product before saving had an ID of 3
        Long expectedId = 4L;

        //  call getId() getters to get the ID of the latest added product
        assertEquals(expectedId, latestProduct.getId());
    }


}