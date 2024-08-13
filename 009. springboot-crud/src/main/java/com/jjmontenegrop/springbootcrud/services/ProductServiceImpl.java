package com.jjmontenegrop.springbootcrud.services;

import com.jjmontenegrop.springbootcrud.entities.Product;
import com.jjmontenegrop.springbootcrud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(Long.valueOf(id));
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> update(Integer id, Product product) {
        Optional<Product> productOptional = productRepository.findById(Long.valueOf(id));
        if (productOptional.isEmpty()) {
            return productOptional;
        }
        Product p = productOptional.orElseThrow();
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        return Optional.of(productRepository.save(p));
    }

    @Transactional
    @Override
    public Optional<Product> deleteById(Integer id) {
        Optional<Product> product = productRepository.findById(Long.valueOf(id));
        product.ifPresent(productRepository::delete);
        return product;
    }
}
