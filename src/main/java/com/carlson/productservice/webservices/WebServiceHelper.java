package com.carlson.productservice.webservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebServiceHelper {

    private RestTemplate restTemplate;

    @Autowired
    public WebServiceHelper(RestTemplate restTemplate) {
        this.restTemplate =restTemplate;
    }

    public CategoryResponse getCategory(String name) {
        String url = "http://localhost:8080/categories/" + name;

        return restTemplate.getForObject(url, CategoryResponse.class);
    }

}
