package com.carlson.productservice.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse {
    private String name;
    private CategoryResponse parent;
    private List<CategoryProduct> categoryProducts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryResponse getParent() {
        return parent;
    }

    public void setParent(CategoryResponse parent) {
        this.parent = parent;
    }

    public List<CategoryProduct> getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(List<CategoryProduct> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }
}
