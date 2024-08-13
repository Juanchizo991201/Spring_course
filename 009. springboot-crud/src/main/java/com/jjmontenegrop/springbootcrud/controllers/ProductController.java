package com.jjmontenegrop.springbootcrud.controllers;

import com.jjmontenegrop.springbootcrud.entities.Product;
import com.jjmontenegrop.springbootcrud.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/list")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (product.isEmpty()) {
            String errorMessage = "Product with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + errorMessage + "\"}");
        }
        return ResponseEntity.ok(product.get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult bindingResult, @PathVariable Integer id) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isEmpty()) {
            String errorMessage = "Product with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + errorMessage + "\"}");
        }
        product.setId(productOptional.get().getId());
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        Optional<Product> product = productService.deleteById(id);
        if (product.isEmpty()) {
            String errorMessage = "Product with ID " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + errorMessage + "\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).body("producto eliminado correctamente");
    }

    /*
    * * Validation method
    */

    private ResponseEntity<?> validation(BindingResult bindingResult) {
        Map< String, String> errors = new HashMap<>();

        bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(),"El campo " + error.getField() + " " + error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
