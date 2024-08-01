package com.values.BookStoreLoginSpring.service;

import com.values.BookStoreLoginSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean verifyUser(String username, String password) throws SQLException{
        return userRepository.verifyUser(username, password);
    }

    @Override
    public int insertUser(String username, String email, String password, String country) throws SQLException{
        return userRepository.insertUser(username, email, password, country);
    }
}
