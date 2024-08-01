package com.values.BookStoreLoginSpring.repository.user;

import java.sql.SQLException;

public interface UserRepo {

    boolean verifyUser(String username, String password) throws SQLException;
    int insertUser(String username, String email, String password, String country) throws SQLException;
}
