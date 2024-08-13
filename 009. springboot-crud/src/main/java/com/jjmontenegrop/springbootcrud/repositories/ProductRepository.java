package com.jjmontenegrop.springbootcrud.repositories;

import com.jjmontenegrop.springbootcrud.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
