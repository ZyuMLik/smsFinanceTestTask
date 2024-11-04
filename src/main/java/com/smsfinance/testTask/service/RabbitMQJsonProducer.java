package com.smsfinance.testTask.service;

import com.smsfinance.testTask.model.Book;
import com.smsfinance.testTask.model.BookEdit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(BookEdit bookEdit) {
        LOGGER.info(String.format("Json Сообщение отправлено -> %s", bookEdit.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, bookEdit);
    }
}
