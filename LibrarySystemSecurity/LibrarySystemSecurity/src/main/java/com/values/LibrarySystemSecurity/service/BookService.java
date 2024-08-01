package com.values.LibrarySystemSecurity.service;

import com.values.LibrarySystemSecurity.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {
    List<Book> getAllBooks();
    Book findBookById(Long id);
}
