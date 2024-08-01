package com.values.BookStoreLoginSpring.repository;

import com.values.BookStoreLoginSpring.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartRepoImpl implements CartRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertBook(String username, int id_book, int quantita, double price) {
        String sqlInsert = "INSERT INTO carrello (id_utente, id_prodotto, quantita, price) VALUES ((SELECT id FROM student WHERE username = ?), ?, ?, ?)";
        return jdbcTemplate.update(sqlInsert, username, id_book, quantita, price);
    }

    @Override
    public List<Cart> cartList() {
        String sql = "SELECT * FROM carrello";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            int id_utente = rs.getInt("id_utente");
            int id_prodotto = rs.getInt("id_prodotto");
            int quantita = rs.getInt("quantita");
            double price = rs.getDouble("price");
            return new Cart(id_utente, id_prodotto, quantita, price);
        });
    }
}
