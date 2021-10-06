package com.carlson.productservice.webservices;

import java.util.List;

public class ProductCategory {
    private final String name;
    private final String description;
    private final String URL;
    private final String currency;
    private final String category;
    private final String subcategory;
    private final List<WebSku> skus;
    private final List<WebMedia> medias;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return URL;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public List<WebSku> getSkus() {
        return skus;
    }

    public List<WebMedia> getMedias() {
        return medias;
    }

    private ProductCategory(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.URL = builder.URL;
        this.currency = builder.currency;
        this.category = builder.category;
        this.subcategory = builder.subcategory;
        this.skus = builder.skus;
        this.medias = builder.medias;
    }

    public static ProductCategory.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private String URL;
        private String currency;
        private String category;
        private String subcategory;
        private List<WebSku> skus;
        private List<WebMedia> medias;

        private Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder URL(String URL) {
            this.URL = URL;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder subcategory(String subcategory) {
            this.subcategory = subcategory;
            return this;
        }

        public Builder skus(List<WebSku> skus) {
            this.skus = skus;
            return this;
        }

        public Builder medias(List<WebMedia> medias) {
            this.medias = medias;
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
