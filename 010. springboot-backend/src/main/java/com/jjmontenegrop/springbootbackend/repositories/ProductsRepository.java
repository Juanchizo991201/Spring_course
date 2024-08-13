package com.jjmontenegrop.springbootbackend.repositories;

import com.jjmontenegrop.springbootbackend.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long>{
}
