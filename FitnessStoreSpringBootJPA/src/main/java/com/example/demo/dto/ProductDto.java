package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    @NotEmpty(message="The name is required")
    private String name;
    @NotEmpty(message="The brand is required")
    private String brand;
    @NotEmpty(message="The category is required")
    private String category;
    @Min(0)
    private double price;
    @Size(min=10, message="Description should be at least 10 characters long")
    @Size(max=2000, message="Description cannot exceed 2000 characters")
    private String description;
    private MultipartFile imageFile;
}
