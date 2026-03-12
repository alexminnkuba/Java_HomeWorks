package org.spring.service;

import org.spring.dao.BookDao;
import org.spring.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookDao bookDao;

    @Override
    public int addBook(Book book) {
        int result = bookDao.saveBook(book);
        return result;
    }
}
