package com.values.BookStoreLoginSpring.repository.cart;

import com.values.BookStoreLoginSpring.model.Books;
import com.values.BookStoreLoginSpring.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartImpl implements CartRepo{
    @Autowired
    DataSource dataSource;

    public int insertBook(String username, int id_book, int quantita, double price) throws SQLException {
        String sqlInsert = "INSERT INTO carrello (id_utente, id_prodotto, quantita, price) VALUES (?, ?, ?, ?)";
        String sqlGetUserId = "SELECT id FROM student WHERE username = ?";

        Connection connection = dataSource.getConnection();

        try (PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
             PreparedStatement psGetUser = connection.prepareStatement(sqlGetUserId)) {

            // Get user ID securely using separate query
            psGetUser.setString(1, username);
            ResultSet rs = psGetUser.executeQuery();

            int userId;
            if (rs.next()) {
                userId = rs.getInt(1);
            } else {
                throw new RuntimeException("User not found: " + username); // Handle user not found
            }

            psInsert.setInt(1, userId);
            psInsert.setInt(2, id_book);
            psInsert.setInt(3, quantita);
            psInsert.setDouble(4, price);

            return psInsert.executeUpdate();
        }
    }
    public List<Cart> cartList() throws SQLException {
        Connection connection= dataSource.getConnection();
        List<Cart> cartList = new ArrayList<>();
        String sql = "select * from carrello";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id_utente1 = rs.getInt(1);
            int id_prodotto1 = rs.getInt(2);
            int quantita1 = rs.getInt(3);
            double price1 = rs.getDouble(4);
//
//            Books book = new Books(id1, name1, description1, image1, quantita1, price1);
//
//            booksList.add(book);
        }
//        return booksList;
        return new ArrayList<>();
    }
}
