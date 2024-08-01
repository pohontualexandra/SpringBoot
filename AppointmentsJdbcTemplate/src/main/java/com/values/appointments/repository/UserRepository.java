package com.values.appointments.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

public interface UserRepository {
    int verifyUser(String email, String password) throws DataAccessException;

    int createUser(String email, String phone, String password);
}
