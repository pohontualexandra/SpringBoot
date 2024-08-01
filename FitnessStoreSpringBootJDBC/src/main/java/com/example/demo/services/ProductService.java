package com.example.demo.services;

import com.example.demo.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(int id);
    void saveProduct(Product product);
    void deleteProductById(int id);
}
