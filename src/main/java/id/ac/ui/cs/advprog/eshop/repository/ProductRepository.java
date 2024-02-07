package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<Product>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product commitEdit(Product product, Product editedProduct) {
        product.setProductName(editedProduct.getProductName());
        product.setProductQuantity(editedProduct.getProductQuantity());
        return product;
    }

    public Product delete(Product product) {
        productData.remove(product);
        return product;
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
}
