package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product;
    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setProductId("5ba5eee5-99a4-43e2-9be2-26f02557d741");
        this.product.setProductName("Lumba Lumba Asli Jawa");
        this.product.setProductQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals("5ba5eee5-99a4-43e2-9be2-26f02557d741", this.product.getProductId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Lumba Lumba Asli Jawa", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getProductQuantity());
    }
}
