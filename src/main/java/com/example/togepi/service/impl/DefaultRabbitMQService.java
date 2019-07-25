package com.example.togepi.service.impl;

import com.example.togepi.config.RabbitMQConfig;
import com.example.togepi.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultRabbitMQService implements RabbitMQService {

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String key, String message) {
        final String exchange = rabbitMQConfig.getExchange();
        rabbitTemplate.convertAndSend(exchange, key, message);
        log.info("Sending message: " + message);
    }

    @Override
//    @RabbitListener(queues = "${togepi.queue.1}")
    public void receive1(String message) {
        log.info("Receiving message in queue 1: " + message);
    }

    @Override
//    @RabbitListener(queues = "${togepi.queue.2}")
    public void receive2(String message) {
        log.info("Receiving message in queue 2: " + message);
    }
}
