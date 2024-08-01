package com.values.AppointmentsJPA.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {
    @Transactional(readOnly = true)
    long selectUserId(String email);

    boolean verifyUser(String email, String password);

    boolean createUser(String email, String phone, String password);
}
