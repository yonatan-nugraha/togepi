package com.example.togepi.service;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);

    Order getOrderById(Long orderId) throws ResourceNotFoundException;

    List<Order> getOrders();
}
