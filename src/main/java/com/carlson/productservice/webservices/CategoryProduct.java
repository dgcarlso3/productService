package com.carlson.productservice.webservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CategoryProduct.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryProduct {
    private final Integer productId;

    private CategoryProduct(Builder builder) {
        this.productId = builder.productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public static CategoryProduct.Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder {
        private Integer productId;

        private Builder() {}

        public Builder productId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public CategoryProduct build() {
            return new CategoryProduct(this);
        }

    }
}
