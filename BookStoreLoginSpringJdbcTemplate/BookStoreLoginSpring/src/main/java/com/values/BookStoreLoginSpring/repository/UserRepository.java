package com.values.BookStoreLoginSpring.repository;


import java.sql.SQLException;

public interface UserRepository {
    boolean verifyUser(String username, String password);
    int insertUser(String username, String email, String password, String country) throws SQLException;
}
