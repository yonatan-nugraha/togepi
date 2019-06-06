package com.example.togepi.service.impl;

import com.example.togepi.config.RabbitMQConfig;
import com.example.togepi.service.RabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRabbitMQService implements RabbitMQService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRabbitMQService.class.getName());

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String key, String message) {
        final String exchange = rabbitMQConfig.getExchange();
        rabbitTemplate.convertAndSend(exchange, key, message);
        logger.info("Sending message: " + message);
    }

    @Override
//    @RabbitListener(queues = "${togepi.queue.1}")
    public void receive1(String message) {
        logger.info("Receiving message in queue 1: " + message);
    }

    @Override
//    @RabbitListener(queues = "${togepi.queue.2}")
    public void receive2(String message) {
        logger.info("Receiving message in queue 2: " + message);
    }
}
