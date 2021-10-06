package com.carlson.productservice.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = CategoryResponse.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryResponse {
    private final String name;
    private final CategoryResponse parent;
    private final List<CategoryProduct> categoryProducts;

    private CategoryResponse(Builder builder) {
        this.name = builder.name;
        this.parent = builder.parent;
        this.categoryProducts = builder.categoryProducts;
    }

    public String getName() {
        return name;
    }

    public CategoryResponse getParent() {
        return parent;
    }

    public List<CategoryProduct> getCategoryProducts() {
        return categoryProducts;
    }

    public static CategoryResponse.Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private String name;
        private CategoryResponse parent;
        private List<CategoryProduct> categoryProducts;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parent(CategoryResponse parent) {
            this.parent = parent;
            return this;
        }

        public Builder categoryProducts(List<CategoryProduct> categoryProducts) {
            this.categoryProducts = categoryProducts;
            return this;
        }

        public CategoryResponse build() {
            return new CategoryResponse(this);
        }

    }
}
