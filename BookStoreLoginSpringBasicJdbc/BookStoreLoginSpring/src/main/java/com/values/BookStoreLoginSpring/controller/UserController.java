package com.values.BookStoreLoginSpring.controller;
import com.values.BookStoreLoginSpring.repository.user.UserRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    private HttpSession httpSession;
    @GetMapping("/login")
    public String home(){
        return "login_page";
    }
    @PostMapping("/login")
    public String getHomePage(@RequestParam String username, @RequestParam String password) throws SQLException {
        if(userRepo.verifyUser(username, password)){
            httpSession.setAttribute("username", username);
            return "redirect:/";
        }else{
            return "login_page";
        }
    }

    @GetMapping("/register")
    public String register_pg(){
        return "registration_page";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email,
                        @RequestParam String password, @RequestParam String country) throws SQLException{
        if(userRepo.insertUser(username, email, password, country) == 1){
            httpSession.setAttribute("username", username);
            return "redirect:/";
        }else{
            return "registration_page";
        }
    }

    @GetMapping("/logout")
    public String logout(){
        httpSession.invalidate();
        return "redirect:/login";
    }
}

