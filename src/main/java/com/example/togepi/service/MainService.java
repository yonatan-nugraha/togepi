package com.example.togepi.service;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.Order;

public interface MainService {

    String hello();

    Order createOrder(Order order);

    Order getOrderById(Long orderId) throws ResourceNotFoundException;
}
