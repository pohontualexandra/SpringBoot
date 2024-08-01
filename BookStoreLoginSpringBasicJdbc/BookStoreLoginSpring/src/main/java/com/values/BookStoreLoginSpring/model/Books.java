package com.values.BookStoreLoginSpring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Books {
    int id;
    String name;
    String description;
    String image;
    int quantity;
    double price;
}
