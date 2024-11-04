package com.smsfinance.testTask.controller;

import com.smsfinance.testTask.model.Book;
import com.smsfinance.testTask.model.BookEdit;
import com.smsfinance.testTask.model.BookStatus;
import com.smsfinance.testTask.service.BookService;
import com.smsfinance.testTask.service.RabbitMQJsonProducer;
import com.smsfinance.testTask.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryOfBooksController {

    @Autowired
    private BookService bookService;
    @Autowired
    private RabbitMQProducer producer;
    @Autowired
    private RabbitMQJsonProducer jsonProducer;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getBook(id);
    }

    @PostMapping("/books")
    public ResponseEntity<String> updateBook(@RequestBody Book book){
//        bookService.saveBook(book);
        producer.sendMessage("Информации о книге обновлена");
        sendJsonMessage(book, BookStatus.POST);
        return ResponseEntity.ok("Информации о книге обновлена");
    }

    @PutMapping("/books")
    public ResponseEntity<String> addNewBook(@RequestBody Book book){
//        bookService.saveBook(book);
        producer.sendMessage("Новая книга добавлена");
        sendJsonMessage(book, BookStatus.PUT);
        return ResponseEntity.ok("Новая книга добавлена");
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id) {
//        bookService.deleteBook(id);
        producer.sendMessage("Книга удалена");
        Book book = new Book();
        book.setId(id);
        sendJsonMessage(book, BookStatus.DELETE);
        return ResponseEntity.ok("Книга удалена");
    }

    private void sendJsonMessage(Book book, BookStatus bookStatus) {
        BookEdit bookEdit = new BookEdit();
        bookEdit.setBook(book);
        bookEdit.setBookStatus(bookStatus);
        jsonProducer.sendJsonMessage(bookEdit);
    }

}
