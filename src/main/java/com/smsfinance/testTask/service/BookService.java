package com.smsfinance.testTask.service;

import com.smsfinance.testTask.model.Book;

import java.util.List;

public interface BookService {
    /**
     * Получение списка всех книг
     * @return Список книг
     */
    public List<Book> getAllBooks();

    /**
     * Получение книги по идентификатору
     * @param id ID книги
     * @return обект книги с заданым ID
     */
    public Book getBook(long id);

    /**
     * Обновление информации о книге,
     * если ID равен нулю, то добавление новой книги
     * @param book Книга данные которой надо добавить или обновить
     */
    public void saveBook(Book book);

    /**
     * Удаление книги по идентификатору
     * @param id ID книги
     */
    public void deleteBook(long id);
}
