package com.values.appointments.service;

import com.values.appointments.model.Sede;

import java.util.List;

public interface SedeService {
    void insertSede(String nome, String phone);

    List<Sede> findAll();

    void updateSede(Sede sede);

    void deleteSede(Long id);

    long selectUserId(String email);
}
