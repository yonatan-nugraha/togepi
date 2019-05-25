package com.example.togepi.service.impl;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.Order;
import com.example.togepi.repository.OrderRepository;
import com.example.togepi.service.MainService;
import com.example.togepi.util.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service("secondMainService")
public class SecondMainService extends AbstractMainService implements MainService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String hello() {
        int a1 = Try.of(() -> 4).get();

        System.out.println(a1);

        int a2 = Try.of(() -> 7/1)
                .filter(isLessThanFive())
                .exceptionally(err -> {
                    System.out.println("Error: " + err.getMessage());
                })
                .orElseGet(() -> 5);

        System.out.println(a2);

        return "hello second";
    }

    private Predicate<Integer> isLessThanFive() {
        return i -> i < 5;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(setOrderDetail(order));
    }

    @Override
    public Order getOrderById(Long orderId) throws ResourceNotFoundException {
        return orderRepository.findById(orderId)
                .map(order -> setOrderDetail(order))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }
}
