package com.values.AppointmentsJPA.controller;


import com.values.AppointmentsJPA.model.Sede;
import com.values.AppointmentsJPA.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sedi")
public class SedeController {
    @Autowired
    SedeService sedeService;

    @GetMapping
    public String sedi(Model model){
        List<Sede> sedi = sedeService.findAll();
        model.addAttribute("sedi", sedi);
        return "sedi";
    }

    @PostMapping
    public String listSedi(Model model){
        return "redirect:/sedi";
    }

    @PostMapping("/modifySede/{id}")
    public String modifySede(@PathVariable long id, @RequestParam("nome") String nome, @RequestParam("phone") String phone){
        System.out.println(id + nome + phone);
        Sede sede = new Sede(id, nome, phone);
        sedeService.updateSede(sede);

        return "redirect:/sedi";
    }

    @PostMapping("/deleteSede")
    public String deleteSede(@RequestParam("id") long id){
        sedeService.deleteSede(id);
        return "redirect:/sedi";
    }

    @PostMapping("/insertSede")
    public String insertSede(@RequestParam("nome") String nome, @RequestParam("phone") String phone){
        sedeService.insertSede(nome, phone);
        return "redirect:/sedi";
    }
}
