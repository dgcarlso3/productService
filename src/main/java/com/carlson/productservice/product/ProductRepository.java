package com.carlson.productservice.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByIdIn(List<Integer> ids);
}