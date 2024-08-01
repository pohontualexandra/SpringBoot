package com.values.BookStoreLoginSpring.repository;

import com.values.BookStoreLoginSpring.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Books> bookList() throws SQLException {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, (rs, rowNum) ->{
            Books book = new Books();
            book.setId(rs.getInt("id"));
            book.setName(rs.getString("name"));
            book.setDescription(rs.getString("description"));
            book.setImage(rs.getString("image"));
            book.setQuantity(rs.getInt("quantita"));
            book.setPrice(rs.getDouble("price"));
            return book;
        });
    }



        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String image = rs.getString("image");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");

            return new Books(id, name, description, image, quantity, price);
        }
}
