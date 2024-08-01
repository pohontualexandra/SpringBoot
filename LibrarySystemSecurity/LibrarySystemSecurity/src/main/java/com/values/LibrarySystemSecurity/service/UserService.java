package com.values.LibrarySystemSecurity.service;


import com.values.LibrarySystemSecurity.model.User;

public interface UserService {
    User findUserById(long id);

    User findByEmail(String email);

    void save(User user);

    User findByUsername(String username);
}
