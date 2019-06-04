package com.example.togepi.service;

import com.example.togepi.model.Item;
import com.example.togepi.model.Order;
import com.example.togepi.repository.OrderRepository;
import com.example.togepi.service.impl.DefaultOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MainServiceTest {

    @InjectMocks
    private final OrderService orderService = new DefaultOrderService();

    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreatingOrder_thenGrandTotalCalculationCorrect() throws Exception {
        final List<Item> items = new ArrayList<>();
        items.add(new Item("Test item", "1", BigDecimal.valueOf(276)));
        items.add(new Item("Test item2", "2", BigDecimal.valueOf(555)));
        items.add(new Item("Test item3", "3", BigDecimal.valueOf(101)));
        final Order order = new Order(items);

        when(orderRepository.save(order)).thenReturn(order);

        final Order orderResult = orderService.createOrder(order);
        final BigDecimal grandTotalResult = BigDecimal.valueOf(980.71);

        assertEquals(grandTotalResult, orderResult.getGrandTotal());
    }

    @Test
    public void whenGettingOrder_thenGrandTotalCalculationCorrect() throws Exception {
        final List<Item> items = new ArrayList<>();
        items.add(new Item("Test item", "1", BigDecimal.valueOf(276)));
        items.add(new Item("Test item2", "2", BigDecimal.valueOf(555)));
        items.add(new Item("Test item3", "3", BigDecimal.valueOf(101)));
        final Order order = new Order(items);
        final Long orderId = Long.valueOf(1);

        when(orderRepository.findById(orderId)).thenReturn(Optional.ofNullable(order));

        final Order orderResult = orderService.getOrderById(orderId);
        final BigDecimal grandTotalResult = BigDecimal.valueOf(980.71);

        assertEquals(grandTotalResult, orderResult.getGrandTotal());
    }
}
