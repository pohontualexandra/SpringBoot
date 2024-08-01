package com.values.BookStoreLoginSpring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepoImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public UserRepoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean verifyUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM student WHERE username = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        return count > 0;
    }

    public int insertUser(String username, String email, String password, String country) throws SQLException{
        String sql = "INSERT INTO student(username, email, password, country) VALUES(?, ?, ?, ?);";
        return jdbcTemplate.update(sql, username, email, password, country);
    }
}
