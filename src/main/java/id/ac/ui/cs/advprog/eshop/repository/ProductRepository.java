package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();
    public void create(Product product) {
        productData.add(product);
    }
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    public void update(String id, Product updatedProduct) {
        for (Product product : productData) {
            if (product.getProductId().equals(id)) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
            }
        }
    }
    public void delete(String id) {
        productData.removeIf(product -> product.getProductId().equals(id));
    }
}
