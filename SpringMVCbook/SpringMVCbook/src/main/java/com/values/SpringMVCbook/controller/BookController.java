package com.values.SpringMVCbook.controller;

import com.values.SpringMVCbook.model.Books;
import com.values.SpringMVCbook.repository.books.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping
    public String books(Model model) throws SQLException {
        List<Books> books = booksRepo.bookList();
        System.out.println(books);
        model.addAttribute("bookList", books);
        return "book_page";
    }
}
