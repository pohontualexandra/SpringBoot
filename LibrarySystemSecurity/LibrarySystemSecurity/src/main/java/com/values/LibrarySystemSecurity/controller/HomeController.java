package com.values.LibrarySystemSecurity.controller;

import com.values.LibrarySystemSecurity.model.Book;
import com.values.LibrarySystemSecurity.model.Loan;
import com.values.LibrarySystemSecurity.model.User;
import com.values.LibrarySystemSecurity.service.BookService;
import com.values.LibrarySystemSecurity.service.LoanService;
import com.values.LibrarySystemSecurity.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home(Model model){
        List<Book> booksList = bookService.getAllBooks();
        model.addAttribute("booksList", booksList);
        System.out.println(booksList);
        return "home";
    }
    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, @RequestParam LocalDate date, Authentication authentication, Model model) {
        Book book = bookService.findBookById(id);

        try{
            Loan loan = loanService.createLoan(id, authentication.getName(), date);
            if (book.isAvailable()) {
                loanService.save(loan);
                book.setAvailable(false);
                return "home";
            } else {
                model.addAttribute("message", "This book will return available on: " + date);
                return "home";
            }
        } catch (Exception e) {
            log.info("Error borrowing book with ID " + id, e);
            return "error";
        }
    }
}
