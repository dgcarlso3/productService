package com.carlson.productservice.product;

import com.carlson.productservice.webservices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private WebServiceHelper webServiceHelper;

    public ProductService() {
    }

    @Autowired
    public ProductService(ProductRepository productRepository, WebServiceHelper webServiceHelper) {
        this.productRepository = productRepository;
        this.webServiceHelper = webServiceHelper;
    }

    public String addNewProduct(String name, String description, String currency) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCurrency(currency);
        productRepository.save(product);
        return "Saved";
    }

    public List<ProductCategory> getProductsByCategoryName(String name) {
        CategoryResponse category = webServiceHelper.getCategory(name);

        ProductCategory.Builder builder = ProductCategory.builder();
        if (category.getParent() == null) {
            builder.category(category.getName());
        } else {
            builder.category(category.getParent().getName());
            builder.subcategory(category.getName());
        }

        List<Integer> productIds = getProductIds(category);
        List<Product> productsById = getProductsById(productIds);

        return buildProductCategoryList(productsById, builder);
    }

    public List<Product> getProductsById(List<Integer> ids) {
        return productRepository.findByIdIn(ids);
    }

    protected List<ProductCategory> buildProductCategoryList(List<Product> productsById, ProductCategory.Builder builder) {
        return productsById.stream().map(product -> {
            return builder.name(product.getName())
                    .currency(product.getCurrency())
                    .description(product.getDescription())
                    .skus(getWebSkus(product))
                    .medias(getWebMedias(product))
                    .build();
        }).collect(Collectors.toList());
    }

    private List<WebMedia> getWebMedias(Product product) {
        return product.getMedias().stream().map(media -> {
            return WebMedia.builder().altText(media.getAltText()).URL(media.getURL()).build();
        }).collect(Collectors.toList());
    }

    private List<WebSku> getWebSkus(Product product) {
        return product.getSkus().stream().map(sku -> {
            return WebSku.builder().name(sku.getName())
                    .inventoryType(sku.getInventoryType())
                    .salePrice(sku.getSalePrice())
                    .retailPrice(sku.getRetailPrice())
                    .quantityAvailable(sku.getQuantityAvailable())
                    .description(sku.getDescription())
                    .build();
        }).collect(Collectors.toList());
    }

    protected List<Integer> getProductIds(CategoryResponse categoryResponse) {
        return categoryResponse.getCategoryProducts().stream()
                .map(categoryProduct -> {
                    return categoryProduct.getProductId();
                })
                .collect(Collectors.toList());
    }

    public Boolean productExists(Integer id) {
        return productRepository.existsById(id);
    }
}
