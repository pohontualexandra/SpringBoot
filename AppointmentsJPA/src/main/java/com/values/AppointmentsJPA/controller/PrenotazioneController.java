package com.values.AppointmentsJPA.controller;

import com.values.AppointmentsJPA.model.PrenotazioneView;
import com.values.AppointmentsJPA.model.Sede;
import com.values.AppointmentsJPA.service.PrenotazioneService;
import com.values.AppointmentsJPA.service.SedeService;
import com.values.AppointmentsJPA.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/prenotazione")
public class PrenotazioneController {
    @Autowired
    SedeService sedeService;
    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    UserService userService;

    public PrenotazioneController(SedeService sedeService, PrenotazioneService prenotazioneService, UserService userService) {
    }

    @GetMapping
    public String prenotazioni(Model model, HttpSession session){
        String email = (String) session.getAttribute("username");
        long userId = userService.selectUserId(email);


        List<PrenotazioneView> prenotazioni = prenotazioneService.findAll(userId);
        model.addAttribute("prenotazioni", prenotazioni);


        List<Sede> sedi = sedeService.findAll();
        model.addAttribute("sedi", sedi);
        String success = (String) session.getAttribute("success");
        if (success != null) {
            model.addAttribute("success", success);
            session.removeAttribute("success");
        }
        return "prenotazione";
    }

    @PostMapping
    public String prenotazioni1(Model model, RedirectAttributes redirectAttributes, HttpSession session, @RequestParam("sede") String sedeId, @RequestParam("date") LocalDate data, @RequestParam("time") LocalTime orario){
        String email = (String) session.getAttribute("username");
        long userId = userService.selectUserId(email);
        prenotazioneService.insert(Long.parseLong(sedeId), userId, data, orario);
        redirectAttributes.addFlashAttribute("success", "Prenotazione effettuata con successo");

//        List<Prenotazione> prenotazioni = prenotazioneService.findAll(userId);
//        model.addAttribute("prenotazioni", prenotazioni);

        return("redirect:/prenotazione");
    }

    @PostMapping("/modifica")
    public String  modify(@RequestParam("pren_id") long prenId, @RequestParam("sede_id") long sedeId, @RequestParam("date") LocalDate date, @RequestParam("time") LocalTime time){
        prenotazioneService.update(prenId, date, time);
        return "redirect:/prenotazione";
    }

    @PostMapping("/delete")
    public String deletePren(@RequestParam("pren_id") long prenId){
        prenotazioneService.delete(prenId);
        return "redirect:/prenotazione";
    }
}
