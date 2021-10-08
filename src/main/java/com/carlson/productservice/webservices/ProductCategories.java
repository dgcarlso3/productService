package com.carlson.productservice.webservices;

import java.util.List;

public class ProductCategories {
    private final Integer totalCount;
    private final List<ProductCategory> productCategories;

    private ProductCategories(Builder builder) {
        this.totalCount = builder.totalCount;
        this.productCategories = builder.productCategories;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public static ProductCategories.Builder builder() {
        return new ProductCategories.Builder();
    }

    public static class Builder {
        private Integer totalCount;
        private List<ProductCategory> productCategories;

        private Builder() {}

        public Builder totalCount(Integer totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder productCategories(List<ProductCategory> productCategories) {
            this.productCategories = productCategories;
            return this;
        }

        public ProductCategories build() {
            return new ProductCategories(this);
        }

    }
}
