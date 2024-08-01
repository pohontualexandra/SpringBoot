package com.example.RolesSecurity.service;

import com.example.RolesSecurity.entities.User;

public interface UserService {

    User findByUsername(String username);

    void save(User user);
}
