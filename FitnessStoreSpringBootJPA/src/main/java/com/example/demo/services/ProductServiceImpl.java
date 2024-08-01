package com.example.demo.services;

import com.example.demo.dto.ProductDto;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(ProductDto productDto, BindingResult result) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
        }
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Validation errors occurred");
        }

        // Save image file
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Files.copy(image.getInputStream(), Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to save image file", ex);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);

        productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void updateProduct(int id, ProductDto productDto, BindingResult result) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        if (result.hasErrors()) {
            throw new IllegalArgumentException("Validation errors occurred");
        }
        if (!productDto.getImageFile().isEmpty()) {
            // Delete old image
            String uploadDir = "public/images/";
            try {
                Files.delete(Paths.get(uploadDir + product.getImageFileName()));
            } catch (Exception ex) {
                throw new RuntimeException("Failed to delete old image file", ex);
            }
            // Save new image file
            MultipartFile image = productDto.getImageFile();
            String storageFileName = new Date().getTime() + "_" + image.getOriginalFilename();
            try {
                Files.copy(image.getInputStream(), Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                throw new RuntimeException("Failed to save new image file", ex);
            }
            product.setImageFileName(storageFileName);
        }

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        // Delete product image
        try {
            Files.delete(Paths.get("public/images/" + product.getImageFileName()));
        } catch (Exception ex) {
            throw new RuntimeException("Failed to delete product image file", ex);
        }
        // Delete the product
        productRepository.delete(product);
    }
}
