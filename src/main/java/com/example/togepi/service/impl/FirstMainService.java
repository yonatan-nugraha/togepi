package com.example.togepi.service.impl;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.Order;
import com.example.togepi.repository.OrderRepository;
import com.example.togepi.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstMainService extends AbstractMainService implements MainService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String hello() {
        return "hello first";
    }

    @Override
    public Order createOrder(Order order) {
        return order;
    }

    @Override
    public Order getOrderById(Long orderId) throws ResourceNotFoundException {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found!"));
    }
}
