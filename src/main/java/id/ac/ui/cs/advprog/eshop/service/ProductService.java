package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public void create(Product product);
    public void commitEdit(Product editedProduct);
    public void delete(Product product);
    public List<Product> findAll();
    public Product findById(String productId);
}