package com.values.AppointmentsJPA.repository;


import com.values.AppointmentsJPA.model.Sede;
import com.values.AppointmentsJPA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;

public interface SedeRepository extends JpaRepository<Sede, Long> {

}
