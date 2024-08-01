package com.values.BookStoreLoginSpring.repository.books;

import com.values.BookStoreLoginSpring.model.Books;

import java.sql.SQLException;
import java.util.List;

public interface BooksRepo {
    List<Books> bookList() throws SQLException;
}
