package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        productService.create(product);
        return "redirect:list";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(Model model, @PathVariable String productId) {
        Product product = productService.findById(productId);
        if (product == null) {
            return "redirect:../list";
        }
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable String productId, @ModelAttribute Product product) {
        if (productId.equals(product.getProductId())) {
            productService.commitEdit(product);
        }
        return "redirect:../list";
    }

    @GetMapping("/delete/{productId}")
    public String editProductPost(@PathVariable String productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            productService.delete(product);
        }
        return "redirect:../list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = productService.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }
}