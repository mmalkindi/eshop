package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {}
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("5ba5eee5-99a4-43e2-9be2-26f02557d741");
        product.setProductName("Lumba Lumba Asli Jawa");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCommitEditAndCheck() {
        Product product = new Product();
        product.setProductId("5ba5eee5-99a4-43e2-9be2-26f02557d741");
        product.setProductName("Lumba Lumba Asli Jawa");
        product.setProductQuantity(100);

        Product editedProduct = new Product();
        editedProduct.setProductId("6ca5eee5-99a4-43e2-9be2-26f02557d741");
        editedProduct.setProductName("Lumba Lumba Asli Lampung");
        editedProduct.setProductQuantity(99);
        productRepository.commitEdit(product, editedProduct);

        assertNotEquals("6ca5eee5-99a4-43e2-9be2-26f02557d741", product.getProductId());
        assertEquals("5ba5eee5-99a4-43e2-9be2-26f02557d741", product.getProductId());

        assertNotEquals("Lumba Lumba Asli Jawa", product.getProductName());
        assertEquals("Lumba Lumba Asli Lampung", product.getProductName());

        assertNotEquals(100, product.getProductQuantity());
        assertEquals(99, product.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("93e4a6d8-a596-4e9e-bd30-25a80b676740");
        product1.setProductName("Lumba Lumba Asli Sumatra");
        product1.setProductQuantity(120);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("8539355a-e19c-4402-86f5-f93ecd5945b2");
        product2.setProductName("Lumba Lumba Asli Sulawesi");
        product2.setProductQuantity(80);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdIfEmpty() {
        assertNull(productRepository.findById("5ba5eee5-99a4-43e2-9be2-26f02557d741"));
    }

    @Test
    void testFindByIdIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("93e4a6d8-a596-4e9e-bd30-25a80b676740");
        product1.setProductName("Lumba Lumba Asli Sumatra");
        product1.setProductQuantity(120);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("8539355a-e19c-4402-86f5-f93ecd5945b2");
        product2.setProductName("Lumba Lumba Asli Sulawesi");
        product2.setProductQuantity(80);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("f77a6d7c-cbd5-44c3-9f06-70064444f8e5");
        product3.setProductName("Lumba Lumba Asli Papua");
        product3.setProductQuantity(20);
        productRepository.create(product3);

        assertNotNull(productRepository.findById("f77a6d7c-cbd5-44c3-9f06-70064444f8e5"));
        assertNull(productRepository.findById("g77a6d7c-cbd5-44c3-9f06-70064444f8e6"));
    }
}
