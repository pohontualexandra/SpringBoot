package com.values.AppointmentsJPA.service;

import com.values.AppointmentsJPA.model.Sede;

import java.util.List;

public interface SedeService {
    void insertSede(String nome, String phone);

    List<Sede> findAll();

    void updateSede(Sede sede);

    void deleteSede(Long id);

}
