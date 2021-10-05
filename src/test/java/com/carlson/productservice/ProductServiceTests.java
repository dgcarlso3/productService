package com.carlson.productservice;

import com.carlson.productservice.webservices.WebServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        Mockito.when(productRepository.findByIdIn(ids)).thenReturn(expected);

        List<Product> actual = service.getProductsById(ids);

        assertSame(expected, actual);
    }
}
