package com.values.AppointmentsJPA.controller;

import com.values.AppointmentsJPA.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/login")
    public String home() {return "login_page";}

    @PostMapping("/login")
    public String getLoginPage(Model model, @RequestParam String email, @RequestParam String password) throws SQLException{
        if(userService.verifyUser(email,password)){
            httpSession.setAttribute("username", email);
            return "redirect:/prenotazione";
        }else{
            model.addAttribute("notFound", "Wrong credentials. Try again.");
            return "login_page";
        }
    }

    @GetMapping("/register")
    public String registerPg() {
        return "registration_page";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,@RequestParam String phone, @RequestParam String password) throws SQLException{
        if(userService.createUser(email, phone, password)){
            httpSession.setAttribute("username", email);
            return "redirect:/prenotazione";
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
