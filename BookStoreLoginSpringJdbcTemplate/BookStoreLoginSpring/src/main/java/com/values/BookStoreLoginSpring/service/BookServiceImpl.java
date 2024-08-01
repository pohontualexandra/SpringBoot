package com.values.BookStoreLoginSpring.service;

import org.springframework.stereotype.Service;

import com.values.BookStoreLoginSpring.model.Books;
import com.values.BookStoreLoginSpring.repository.BookRepository;
import com.values.BookStoreLoginSpring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Books> bookList() throws SQLException {
        return bookRepository.bookList();
    }
}

