package com.example.togepi.scheduler;

import com.example.togepi.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RabbitMQScheduler {

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox", "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    private final AtomicInteger index = new AtomicInteger(0);

    private final AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private RabbitMQService rabbitMQService;

//    @Scheduled(fixedRate = 1000)
    public void executeRabbitMQSender() throws Exception {
        final StringBuilder builder = new StringBuilder("Hello to ");
        if (this.index.incrementAndGet() == keys.length) {
            this.index.set(0);
        }
        final String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.incrementAndGet());
        final String message = builder.toString();

        rabbitMQService.send(key, message);
    }
}
