package com.values.appointments.repository;

import com.values.appointments.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final String SELECT = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
    private final String INSERT = "INSERT INTO users(email, phone, password) VALUES(?,?,?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int verifyUser(String email, String password){
        /*
        jdbcTemplate.query(SELECT, new Object[]{email, password}, (rs, rowNum)->{
            User user = new User();
            user.setUserId(rs.getInt("id"));
            return user;
        });
         */
        return jdbcTemplate.queryForObject(SELECT, Integer.class, email, password);
    }
    @Override
    public int createUser(String email, String phone, String password){
        return jdbcTemplate.update(INSERT, email, phone, password);
    }
}
