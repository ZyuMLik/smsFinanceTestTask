package com.smsfinance.testTask.service;

import com.smsfinance.testTask.model.Book;
import com.smsfinance.testTask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);

    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
