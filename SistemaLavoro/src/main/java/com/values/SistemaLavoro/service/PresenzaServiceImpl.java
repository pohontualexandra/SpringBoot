package com.values.SistemaLavoro.service;

import com.values.SistemaLavoro.model.Presenza;
import com.values.SistemaLavoro.model.User;
import com.values.SistemaLavoro.repository.PresenzaRepository;
import com.values.SistemaLavoro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class PresenzaServiceImpl implements PresenzaService{

    @Autowired
    PresenzaRepository presenzaRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Presenza> findAllByUserId(Long userId) {
        return presenzaRepository.findAllByUserId(userId);
    }

    @Override
    public double selectTotals(Long id){
        return presenzaRepository.selectTotals(id);
    }

    @Override
    public Presenza creaPresenza(String username, Presenza presenza){
        User user = userRepository.findByUsername(username);
        Duration durata = Duration.between(presenza.getIngresso(), presenza.getUscita());
        long oreLavoro = durata.toHours();
        System.out.println(oreLavoro);

        presenza.setUser(user);
        presenza.setOreLavoro((double) oreLavoro);

        return presenza;
    }
    @Override
    public void save(Presenza presenza){
        presenzaRepository.save(presenza);
    }

    @Override
    public Presenza findPresenzaById(Long id){
        return presenzaRepository.findPresenzaById(id);
    }
}
