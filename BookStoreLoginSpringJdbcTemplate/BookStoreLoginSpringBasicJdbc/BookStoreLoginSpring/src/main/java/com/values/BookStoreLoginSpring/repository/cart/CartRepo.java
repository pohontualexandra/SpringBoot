package com.values.BookStoreLoginSpring.repository.cart;

import java.sql.SQLException;

public interface CartRepo {
    int insertBook(String username, int id_book, int quantita, double price) throws SQLException;
}
