package com.values.BookStoreLoginSpring.repository;

import com.values.BookStoreLoginSpring.model.Books;
import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    List<Books> bookList() throws SQLException;
}

