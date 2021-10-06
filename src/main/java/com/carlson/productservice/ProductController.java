package com.carlson.productservice;

import com.carlson.productservice.webservices.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path="/add")
    public String addNewProduct (@RequestParam String name,
                                               @RequestParam String description,
                                               @RequestParam String currency) {
        return productService.addNewProduct(name, description, currency);
    }

    @GetMapping(path="")
    public List<Product> getProductsById(@RequestParam List<Integer> ids) {
        return productService.getProductsById(ids);
    }

    @GetMapping(path="/categories/{name}")
    public List<ProductCategory> getProductsForCategory(@PathVariable String name) {
        return productService.getProductsByCategoryName(name);
    }

}