package com.values.SistemaLavoro.service;


import com.values.SistemaLavoro.model.User;

public interface UserService {

    User findByUsername(String username);

    void save(User request);
}
