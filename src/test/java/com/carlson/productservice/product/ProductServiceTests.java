package com.carlson.productservice.product;

import com.carlson.productservice.media.Media;
import com.carlson.productservice.sku.Sku;
import com.carlson.productservice.webservices.CategoryProduct;
import com.carlson.productservice.webservices.CategoryResponse;
import com.carlson.productservice.webservices.ProductCategory;
import com.carlson.productservice.webservices.WebServiceHelper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTests {

    @MockBean
    ProductRepository productRepository;

    @MockBean
    WebServiceHelper webServiceHelper;

    @Spy
    private ProductService service;

    @BeforeEach
    public void beforeEach() {
        service = new ProductService(productRepository, webServiceHelper);
    }

    @Test
    public void addNewProduct_returnsSaved() {
        String result = service.addNewProduct("", "", "");

        assertEquals("Saved", result);
    }

    @Test
    public void addNewProduct_callsSavesOnRepository() {
        service.addNewProduct("the name",
                "the description",
                "the  currency");

        Mockito.verify(productRepository).save(Mockito.any());
    }

    @Test
    public void addNewProduct_savesObjectWithCorrectParams() {
        service.addNewProduct("the name", "the description", "the currency");

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productRepository).save(captor.capture());

        Product result = captor.getValue();
        assertEquals("the name", result.getName());
        assertEquals("the description", result.getDescription());
        assertEquals("the currency", result.getCurrency());
    }

    @Test
    public void getProductsById_callsSavesOnRepository() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        service.getProductsById(ids);

        Mockito.verify(productRepository).findByIdIn(ids);
    }

    @Test
    public void getProductsById_returnsResultFromRepository() {
        List<Product> expected = new ArrayList<>();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        when(productRepository.findByIdIn(ids)).thenReturn(expected);

        List<Product> actual = service.getProductsById(ids);

        assertSame(expected, actual);
    }

    @Test
    public void getProductsByCategoryName_callsGetCategory() {
        service = mock(ProductService.class);
        // Test: calls getCategory
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setProductId(42);
        CategoryResponse expected = new CategoryResponse();
        expected.setCategoryProducts(List.of(categoryProduct));
        when(webServiceHelper.getCategory("foo")).thenReturn(expected);
        // Test: gets Product Id list
        List<Integer> ids = new ArrayList<>();
        when(service.getProductIds(expected)).thenReturn(ids);
        // Test: gets Products by id list
        List<Product> products = new ArrayList<>();
        when(service.getProductsById(ids)).thenReturn(products);
        // Test: returns
        List<ProductCategory> productCategories = new ArrayList<>();
        when(service.buildProductCategoryList(eq(products), Mockito.any())).thenReturn(productCategories);

        List<ProductCategory> actual = service.getProductsByCategoryName("foo");

        assertThat(actual, is(productCategories));

    }

    @Test
    public void buildProductCategoryList() {
        List<Product> products = new ArrayList<>();
        products.add(getProduct(1));
        products.add(getProduct(2));
        products.add(getProduct(3));

        List<ProductCategory> productCategories = service.buildProductCategoryList(products, ProductCategory.builder());

        assertThat(productCategories, hasSize(3));
        testProductCategory(productCategories, 1);
        testProductCategory(productCategories, 2);
        testProductCategory(productCategories, 3);

    }

    @Test
    public void getProductIds_returnsListOfProductIds() {
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setProductId(42);
        CategoryProduct categoryProduct2 = new CategoryProduct();
        categoryProduct2.setProductId(4242);
        CategoryProduct categoryProduct3 = new CategoryProduct();
        categoryProduct3.setProductId(424242);
        List<CategoryProduct> categoryProducts = Lists.newArrayList(categoryProduct, categoryProduct2, categoryProduct3);
        CategoryResponse response = new CategoryResponse();
        response.setCategoryProducts(categoryProducts);

        List<Integer> productIds = service.getProductIds(response);

        assertThat(productIds, containsInAnyOrder(42, 4242, 424242));
    }

    private void testProductCategory(List<ProductCategory> productCategories, int key) {
        assertThat(productCategories, hasItem(allOf(
                hasProperty("name", is("name"+key)),
                hasProperty("currency", is("currency"+key)),
                hasProperty("description", is("description"+key))
        )));
    }

    private Product getProduct(int key) {
        Product product = new Product();
        product.setName("name"+key);
        product.setCurrency("currency"+key);
        product.setDescription("description"+key);
        Sku sku =  new Sku();
        sku.setName("skuname"+key);
        product.setSkus(List.of(sku));
        Media media = new Media();
        media.setURL("URL" + key);
        product.setMedias(List.of(media));
        return product;
    }
}
