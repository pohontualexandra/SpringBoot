package com.values.BookStoreLoginSpring.service;

import com.values.BookStoreLoginSpring.model.Cart;
import com.values.BookStoreLoginSpring.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> cartList() throws SQLException{
        return cartRepository.cartList();
    }
    @Override
    public int insertBook(String username, int id_book, int quantita, double price) throws SQLException{
        return cartRepository.insertBook(username, id_book, quantita, price);
    }
}
