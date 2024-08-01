package com.values.AppointmentsJPA.repository;

import com.values.AppointmentsJPA.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

}
