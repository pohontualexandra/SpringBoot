package com.values.BookStoreLoginSpring.service;

import com.values.BookStoreLoginSpring.model.Cart;

import java.sql.SQLException;
import java.util.List;

public interface CartService {
    List<Cart> cartList() throws SQLException;

    int insertBook(String username, int id_book, int quantita, double price) throws SQLException;
}
