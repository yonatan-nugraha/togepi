package com.example.togepi.service;

public interface RabbitMQService {

    void send(String key, String message);

    void receive1(String message);

    void receive2(String message);
}
