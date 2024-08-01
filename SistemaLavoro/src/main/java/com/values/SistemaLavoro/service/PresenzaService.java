package com.values.SistemaLavoro.service;

import com.values.SistemaLavoro.model.Presenza;

import java.util.List;

public interface PresenzaService {

    List<Presenza> findAllByUserId(Long userId);

    double selectTotals(Long id);

    Presenza creaPresenza(String username, Presenza presenza);

    void save(Presenza presenza);

    Presenza findPresenzaById(Long id);
}
