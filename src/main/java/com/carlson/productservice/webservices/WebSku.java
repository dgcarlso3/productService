package com.carlson.productservice.webservices;

import com.carlson.productservice.InventoryTypeEnum;

import java.math.BigDecimal;

public class WebSku {
    private final String name;
    private final String description;
    private final BigDecimal retailPrice;
    private final BigDecimal salePrice;
    private final InventoryTypeEnum inventoryType;
    private final int quantityAvailable;

    private WebSku(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.retailPrice = builder.retailPrice;
        this.salePrice = builder.salePrice;
        this.inventoryType = builder.inventoryType;
        this.quantityAvailable = builder.quantityAvailable;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public InventoryTypeEnum getInventoryType() {
        return inventoryType;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public static WebSku.Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private String description;
        private BigDecimal retailPrice;
        private BigDecimal salePrice;
        private InventoryTypeEnum inventoryType;
        private int quantityAvailable;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder retailPrice(BigDecimal retailPrice) {
            this.retailPrice = retailPrice;
            return this;
        }

        public Builder salePrice(BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Builder inventoryType(InventoryTypeEnum inventoryType) {
            this.inventoryType = Builder.this.inventoryType;
            return this;
        }

        public Builder quantityAvailable(int quantityAvailable) {
            this.quantityAvailable = Builder.this.quantityAvailable;
            return this;
        }

        public WebSku build() {
            return new WebSku(this);
        }

    }

}
