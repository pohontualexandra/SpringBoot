package com.values.BookStoreLoginSpring.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepoImpl implements UserRepo {
    @Autowired
    DataSource dataSource;

    public boolean verifyUser(String username, String password) throws SQLException {
        String sql = "Select * from student where username = ? and password = ?";
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
    public int insertUser(String username, String email, String password, String country) throws SQLException{
        String sql = "INSERT INTO student(username, email, password, country) VALUES(?, ?, ?, ?);";
        Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, country);
        return ps.executeUpdate();
    }
}
