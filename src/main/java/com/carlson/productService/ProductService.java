package com.carlson.productService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService() {}

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addNewProduct (String name, String description, String currency) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCurrency(currency);
        productRepository.save(product);
        return "Saved";
    }

}
