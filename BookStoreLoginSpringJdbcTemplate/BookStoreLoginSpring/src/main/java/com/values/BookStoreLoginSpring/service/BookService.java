package com.values.BookStoreLoginSpring.service;

import com.values.BookStoreLoginSpring.model.Books;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<Books> bookList() throws SQLException;
}
