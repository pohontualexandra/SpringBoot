package com.values.BookStoreLoginSpring.service;

import java.sql.SQLException;

public interface UserService {
    boolean verifyUser(String username, String password) throws SQLException;

    int insertUser(String username, String email, String password, String country) throws SQLException;
}
