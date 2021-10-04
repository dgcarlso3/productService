package com.carlson.productService;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

}