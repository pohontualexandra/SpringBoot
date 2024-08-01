package com.example.demo.repository;

import com.example.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class ProductMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setBrand(rs.getString("brand"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            product.setDescription(rs.getString("description"));
            product.setCreatedAt(rs.getDate("created_at"));
            product.setImageFileName(rs.getString("image_file_name"));
            return product;
        }
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products ORDER BY id DESC", new ProductMapper());
    }

    public Optional<Product> findById(int id) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM products WHERE id = ?", new Object[]{id}, new ProductMapper());
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    public void save(Product product) {
        if (product.getId() == 0) {
            jdbcTemplate.update(
                    "INSERT INTO products (name, brand, category, price, description, created_at, image_file_name) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    product.getName(), product.getBrand(), product.getCategory(), product.getPrice(), product.getDescription(), product.getCreatedAt(), product.getImageFileName()
            );
        } else {
            jdbcTemplate.update(
                    "UPDATE products SET name = ?, brand = ?, category = ?, price = ?, description = ?, image_file_name = ? WHERE id = ?",
                    product.getName(), product.getBrand(), product.getCategory(), product.getPrice(), product.getDescription(), product.getImageFileName(), product.getId()
            );
        }
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }
}
