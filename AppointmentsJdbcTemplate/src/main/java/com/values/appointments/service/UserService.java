package com.values.appointments.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    int verifyUser(String email, String password);

    int createUser(String email, String phone, String password);
}
