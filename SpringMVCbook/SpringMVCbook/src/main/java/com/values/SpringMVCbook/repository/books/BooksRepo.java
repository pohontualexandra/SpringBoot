package com.values.SpringMVCbook.repository.books;

import com.values.SpringMVCbook.model.Books;

import java.sql.SQLException;
import java.util.List;

public interface BooksRepo {
    List<Books> bookList() throws SQLException;
}
