package com.values.BookStoreLoginSpring.controller;

import com.values.BookStoreLoginSpring.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
@RequestMapping("/carrello")
public class CartController {

    @Autowired
    private HttpSession session;
    @GetMapping
    public String cart(HttpSession session, Model model) throws SQLException{
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "carrello";
    }
}
