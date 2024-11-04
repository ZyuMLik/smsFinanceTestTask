package com.smsfinance.testTask.service;

import com.smsfinance.testTask.model.Book;
import com.smsfinance.testTask.model.BookEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    @Autowired
    private BookService bookService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.json.name}")
    public void consumeJsonMessage(BookEdit bookEdit) {
        LOGGER.info(String.format("Получено сообщение в формате JSON -> %s", bookEdit));
        Book book = bookEdit.getBook();
        switch (bookEdit.getBookStatus()){
            case PUT, POST -> bookService.saveBook(book);
            case DELETE -> bookService.deleteBook(book.getId());
        }

    }


}
