package com.example.demo.myfirstapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product productWithoutId;
    private Product productWithId;

    @BeforeEach
    //  setup two Product objects (with ID & without ID)
    void setUp() {
        productWithoutId = new Product("Test1", 79.99);
        productWithId = new Product(20200113253L, "Test2", 29.99);
    }

    //  Test IDs
    @Test
    void testGetIdProductWithoutId() {
        assertNull(productWithoutId.getId());
    }

    @Test
    void testGetIdProductWithId() {
        assertEquals(20200113253L, productWithId.getId());
    }

    // Test getName
    @Test
    void testGetNameProductWithoutId() {
        assertEquals("Test1", productWithoutId.getName());
    }
    @Test
    void testGetNameProductWithId() {
        assertEquals("Test2", productWithId.getName());
    }

    // Test getPrice
    @Test
    void testGetPriceProductWithoutId() {
        assertEquals(79.99, productWithoutId.getPrice());
    }

    @Test
    void testGetPriceProductWithId() {
        assertEquals(29.99, productWithId.getPrice());
    }

    // Test setId
    @Test
    void testSetId() {
        //  set product ID for productWithoutId objects, since initially, productWithoutId doesn't have an ID yet
        productWithoutId.setId(20200113254L);

        assertEquals(20200113254L, productWithoutId.getId());
    }

    // Test getName
    @Test
    void testSetName() {
        productWithId.setName("TestItem");

        assertEquals("TestItem", productWithId.getName());
    }

    // Test getPrice
    @Test
    void testSetPrice() {
        productWithId.setPrice(59.99);

        assertEquals(59.99, productWithId.getPrice());
    }

    //  Test toString
    @Test
    void testProductWithIdToString() {
        String expected = "Product{id=20200113253, name='Test2', price=29.99}";
        assertEquals(expected, productWithId.toString());
    }

    @Test
    void testProductWithoutIdToString() {
        String expected = "Product{id=null, name='Test1', price=79.99}";
        assertEquals(expected, productWithoutId.toString());
    }

    //  Test equals
    @Test
    void testComparingTheSameProduct() {
        //  test for an object calling itself in equals method
        assertTrue(productWithId.equals(productWithId));
    }

    @Test
    void testComparingProductWithIdWithProductWithoutId() {
        //  comparing Product initialized with ID and without ID (productWithId & productWithoutId)
        assertFalse(productWithId.equals(productWithoutId));
    }

    @Test
    void testComparingTwoDifferentProductsWithSameAttributesWithId() {
        //  created 2 new objects both with the same attributes (including ID)
        Product product1 = new Product(23400534276L, "Product1", 75.00);
        Product product2 = new Product(23400534276L, "Product1", 75.00);

        assertTrue(product1.equals(product2));
    }

    @Test
    void testComparingTwoDifferentProductsWithSameAttributesWithoutId() {
        //  created 2 new objects both with the same attributes (excluding ID)
        Product product3 = new Product("Product2", 86.00);
        Product product4 = new Product("Product2", 86.00);

        assertTrue(product3.equals(product4));
    }

    //  Test hashCode
    @Test
    void testProductWithIdHashCode() {
        //  hash first each attributes by utilizing getters
        int expected = Objects.hash(
            productWithId.getId(),
            productWithId.getName(),
            productWithId.getPrice()
        );

        assertEquals(expected , productWithId.hashCode());
    }

    @Test
    void testProductWithoutIdHashCode() {
        //  hash first each attributes by utilizing getters
        int expected = Objects.hash(
                productWithoutId.getId(),
                productWithoutId.getName(),
                productWithoutId.getPrice()
        );

        assertEquals(expected , productWithoutId.hashCode());
    }


}