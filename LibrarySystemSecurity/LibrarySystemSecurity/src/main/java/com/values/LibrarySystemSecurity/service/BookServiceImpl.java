package com.values.LibrarySystemSecurity.service;

import com.values.LibrarySystemSecurity.model.Book;
import com.values.LibrarySystemSecurity.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(Long id){
        return bookRepository.findBookById(id);
    }
}
