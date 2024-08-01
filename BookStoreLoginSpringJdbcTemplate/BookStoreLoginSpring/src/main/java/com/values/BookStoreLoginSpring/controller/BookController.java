package com.values.BookStoreLoginSpring.controller;

import com.values.BookStoreLoginSpring.model.Books;
import com.values.BookStoreLoginSpring.service.BookService;
import com.values.BookStoreLoginSpring.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    CartService cartService;

    @Autowired
    private HttpSession session;

    @GetMapping
    public String books(Model model, HttpSession session) throws SQLException {
        List<Books> books = bookService.bookList();
        String username = (String) session.getAttribute("username");
        System.out.println(books);
        model.addAttribute("bookList", books);
        model.addAttribute("username", username);
        return "book_page";
    }

    @PostMapping("/carrello/{id}/{prezzo}")
    public String getCarrello(@PathVariable int id, @RequestParam int quantity, @PathVariable double prezzo, Model model) throws SQLException {
        String username = (String) session.getAttribute("username");
        cartService.insertBook(username, id, quantity, prezzo);
        System.out.println(id + " " + quantity);
        return "redirect:/";
    }
}
