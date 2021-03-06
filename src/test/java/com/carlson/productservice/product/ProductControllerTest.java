package com.carlson.productservice.product;


import com.carlson.productservice.webservices.ProductCategories;
import com.carlson.productservice.webservices.ProductCategory;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ProductService productService;

    @Test
    public void addNewProduct_returnsOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/products/add")
                        .param("name", "")
                        .param("description", "")
                        .param("currency",""))
                .andExpect(status().isOk());
    }

    @Test
    public void addNewProduct_callsServiceWithNameAndDescriptionAndCurrency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/products/add")
                .param("name", "This name")
                .param("description", "This description")
                .param("currency","This currency"));

        Mockito.verify(productService).addNewProduct("This name", "This description", "This currency");
    }

    @Test
    public void addNewProduct_returnsResponseFromServiceLayer() throws Exception {
        Mockito.when(productService.addNewProduct("1", "2", "3")).thenReturn("Floof");
        mvc.perform(MockMvcRequestBuilders.post("/products/add")
                        .param("name", "1")
                        .param("description", "2")
                        .param("currency","3"))
                .andExpect(content().string(equalTo("Floof")));
    }

    @Test
    public void getProductsById_returnsOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products")
                        .param("ids", "1")
                        .param("ids", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsById_callsServiceWithNameAndDescriptionAndCurrency() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products")
                .param("ids", "1")
                .param("ids", "2"));

        ArgumentCaptor<List<Integer>> captor = ArgumentCaptor.forClass(ArrayList.class);
        Mockito.verify(productService).getProductsById(captor.capture());
        List<Integer> allValues = captor.getValue();
        assertThat(allValues, containsInAnyOrder(1,2));
    }

    @Test
    public void getProductsById_returnsResponseFromServiceLayer() throws Exception {
        List<Product> expected = new ArrayList<>();
        Product e = new Product();
        e.setId(1);
        e.setName("Foo");
        expected.add(e);
        Mockito.when(productService.getProductsById(Mockito.anyList())).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders.get("/products")
                .param("ids", "1")
                .param("ids", "2"))
                .andExpect(content().json("[{\"id\":1, \"name\":\"Foo\"}]}"));
    }

    @Test
    public void getProductsForCategory_returnsOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products/categories/foo"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductsForCategory_callsServiceWithName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products/categories/foo"));

        Mockito.verify(productService).getProductsByCategoryName("foo");
    }

    @Test
    public void getProductsForCategory_returnsResponseFromServiceLayer() throws Exception {
        List<ProductCategory> productCategoryList = new ArrayList<>();
        ProductCategory e = ProductCategory.builder().name("product").build();
        productCategoryList.add(e);
        ProductCategories expected = ProductCategories.builder().totalCount(1).productCategories(productCategoryList).build();
        Mockito.when(productService.getProductsByCategoryName("foo")).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders.get("/products/categories/foo"))
                .andExpect(content().json("{\"totalCount\":1,\"productCategories\":[{\"name\":\"product\",\"description\":null,\"currency\":null,\"category\":null,\"subcategory\":null,\"skus\":null,\"medias\":null,\"url\":null}]}"));
    }

    @Test
    public void getProductExistsForId_returnsOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products/42/exists"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProductExistsForId_callsServiceWithName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/products/42/exists"));

        Mockito.verify(productService).productExists(42);
    }

    @Test
    public void getProductExistsForId_returnsResultFromService() throws Exception {
        Mockito.when(productService.productExists(42)).thenReturn(false);
        mvc.perform(MockMvcRequestBuilders.get("/products/42/exists")).andExpect(content().string("false"));

        Mockito.when(productService.productExists(42)).thenReturn(true);
        mvc.perform(MockMvcRequestBuilders.get("/products/42/exists")).andExpect(content().string("true"));
    }
}