package com.example.togepi.controller;

import com.example.togepi.exception.ResourceNotFoundException;
import com.example.togepi.model.Item;
import com.example.togepi.model.Order;
import com.example.togepi.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private OrderService orderService;

    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void givenItems_whenCreatingOrder_thenReturnSuccess() throws Exception {
        final List<Item> items = new ArrayList<>();
        items.add(new Item("Test item", "1", BigDecimal.valueOf(500)));
        items.add(new Item("Test item2", "2", BigDecimal.valueOf(500)));
        final Order order = new Order(items);

        when(orderService.createOrder(order)).thenReturn(order);
        this.mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenEmptyItem_whenCreatingOrder_thenReturnBadRequest() throws Exception {
        final List<Item> items = new ArrayList<>();
        final Order order = new Order(items);

        this.mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGettingExistingOrder_thenReturnSuccess() throws Exception {
        final Long orderId = Long.valueOf(1);

        when(orderService.getOrderById(orderId)).thenReturn(new Order());
        this.mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGettingNonExistingOrder_thenReturnNotFound() throws Exception {
        final Long orderId = Long.valueOf(2);

        when(orderService.getOrderById(orderId)).thenThrow(new ResourceNotFoundException("Order not found"));
        this.mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isNotFound());
    }
}