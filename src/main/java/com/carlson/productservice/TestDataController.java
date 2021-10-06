package com.carlson.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/createtestdata")
public class TestDataController {
    @Autowired
    private TestDataService testDataService;

    @GetMapping(path="/skus")
    public String saveSkus() {
        return testDataService.makeTestSkus();
    }

    @GetMapping(path="/medias")
    public String saveMedias() {
        return testDataService.makeTestMedias();
    }


}