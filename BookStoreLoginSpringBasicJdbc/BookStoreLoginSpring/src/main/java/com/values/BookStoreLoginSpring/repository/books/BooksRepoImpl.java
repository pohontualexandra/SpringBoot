package com.values.BookStoreLoginSpring.repository.books;

import com.values.BookStoreLoginSpring.model.Books;
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
public class BooksRepoImpl implements BooksRepo{
    @Autowired
    DataSource dataSource;
    public List<Books> bookList() throws SQLException {
        Connection connection= dataSource.getConnection();
        List<Books> booksList = new ArrayList<>();
        String sql = "select * from books";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id1 = rs.getInt(1);
            String name1 = rs.getString(2);
            String description1 = rs.getString(3);
            String image1 = rs.getString(4);
            int quantita1 = rs.getInt(5);
            double price1 = rs.getDouble(6);

            Books book = new Books(id1, name1, description1, image1, quantita1, price1);

            booksList.add(book);
        }
        return booksList;
    }
}
