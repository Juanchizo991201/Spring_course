package com.jjmontenegrop.springbootcrud.services;

import com.jjmontenegrop.springbootcrud.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Integer id);

    Product save(Product product);

    Optional<Product> update(Integer id, Product product);

    Optional<Product> deleteById(Integer id);
}
