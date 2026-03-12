package org.spring.dao;

import org.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveBook(Book book){
        String sql = "INSERT INTO books(title, author, year) VALUE(?,?,?)";
        int row = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getYear());
        return row;
    }
}
