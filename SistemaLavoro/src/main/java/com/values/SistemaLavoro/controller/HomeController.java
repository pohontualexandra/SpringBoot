package com.values.SistemaLavoro.controller;

import com.values.SistemaLavoro.model.Presenza;
import com.values.SistemaLavoro.model.User;
import com.values.SistemaLavoro.repository.PresenzaRepository;
import com.values.SistemaLavoro.service.PresenzaService;
import com.values.SistemaLavoro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private UserService userService;

    @Autowired
    private PresenzaService presenzaService;


    @GetMapping("/home")
    public String inserisciPresenza(){
        return "home";
    }

    @PostMapping("/home")
    public String inserisciPresenza(@ModelAttribute Presenza presenza, Model model, Authentication authentication) {
        try {
            String username = authentication.getName();
            presenzaService.creaPresenza(username, presenza);
            presenzaService.save(presenza);
            return "home";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Login error");
            return "login";
        }
    }


    @GetMapping("/presenza")
    public String home(@ModelAttribute Presenza presenza, Model model, Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userService.findByUsername(username);
            List<Presenza> presenzeList = presenzaService.findAllByUserId(user.getId());
            double totaleOre = presenzaService.selectTotals(user.getId());
            model.addAttribute("presenzeList", presenzeList);
            model.addAttribute("totaleOre", totaleOre);
            model.addAttribute("userId", user.getId());
            System.out.println(presenzeList);
            return "lista_presenze";
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Login error");
            return "login";
        }
    }
}
