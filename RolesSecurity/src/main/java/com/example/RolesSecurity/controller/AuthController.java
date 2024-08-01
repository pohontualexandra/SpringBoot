package com.example.RolesSecurity.controller;

import com.example.RolesSecurity.entities.User;
import com.example.RolesSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginProcess(@ModelAttribute User user, Model model){
        try {
            System.out.println(user.getUsername() +  user.getPassword());
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword());
            authenticationManager.authenticate(authentication);
            return "home";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        if(userService.findByUsername(user.getUsername()) != null){
            return "register";
        }else{
            userService.save(user);
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model){
        model.addAttribute("username", authentication.getName());
        return "home";
    }

    @GetMapping("/admin")
    public String admin(Authentication authentication, Model model){
        model.addAttribute("username", authentication.getName());
        return "admin";
    }
}


