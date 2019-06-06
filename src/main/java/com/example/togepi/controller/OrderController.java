package com.example.togepi.controller;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.Order;
import com.example.togepi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping(value = "/orders/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) throws ResourceNotFoundException {
        return orderService.getOrderById(orderId);
    }

    @PostMapping(value = "/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderService.createOrder(order);
    }
//
//    @PutMapping(value = "/orders/{orderId}")
//    public Order updateOrder(@PathVariable Long orderId, @Valid @RequestBody Order order) throws ResourceNotFoundException {
//        return mainService.updateOrder(orderId, order);
//    }
//
//    @DeleteMapping(value = "/orders/{orderId}")
//    public Map<String, Boolean> deleteOrder(@PathVariable Long orderId) throws ResourceNotFoundException {
//        return mainService.deleteOrders(orderId);
//    }
}
