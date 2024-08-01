package com.values.appointments.repository;

import com.values.appointments.model.Sede;

import java.sql.SQLException;
import java.util.List;

public interface SedeRepository {
    void insertSede(String nome, String phone);

    long selectUserId(String email);

    List<Sede> findAll() throws SQLException;

    void updateSede(Sede sede);

    void deleteSede(long id);
}
