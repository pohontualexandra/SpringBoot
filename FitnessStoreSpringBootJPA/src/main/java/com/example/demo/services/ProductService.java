package com.example.demo.services;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void createProduct(ProductDto productDto, BindingResult result);
    Product findById(int id);
    void updateProduct(int id, ProductDto productDto, BindingResult result);
    void deleteProduct(int id);
}
