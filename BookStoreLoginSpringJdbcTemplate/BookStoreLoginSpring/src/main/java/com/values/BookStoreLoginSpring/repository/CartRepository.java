package com.values.BookStoreLoginSpring.repository;

import com.values.BookStoreLoginSpring.model.Cart;

import java.util.List;

public interface CartRepository {
    int insertBook(String username, int id_book, int quantita, double price);

    List<Cart> cartList();
}
