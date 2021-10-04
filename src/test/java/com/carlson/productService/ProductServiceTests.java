package com.carlson.productService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTests {

    @MockBean
    ProductRepository productRepository;

    @Spy
    private ProductService service;

    @BeforeEach
    public void beforeEach() {
        service = new ProductService(productRepository);
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

}
