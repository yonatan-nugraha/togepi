package com.example.togepi.service.impl;

import com.example.togepi.constant.Category;
import com.example.togepi.constant.OrderStatus;
import com.example.togepi.model.Item;
import com.example.togepi.model.Order;
import com.example.togepi.service.MainService;
import com.example.togepi.util.MainUtil;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractMainService implements MainService {

    protected Order setOrderDetail(Order order) {
        final List<Item> items = order.getItems();
        items.stream().forEach(item -> {
            final BigDecimal price = item.getPrice();
            final String categoryId = item.getCategoryId();
            final Category category = Category.findById(categoryId);
            final String categoryName = category.getName();
            final BigDecimal tax = MainUtil.calculateTax(item);
            final BigDecimal amount = item.getPrice().add(tax);

            item.setTax(tax);
            item.setAmount(amount);
            item.setCategoryName(categoryName);

            order.setPriceSubtotal(order.getPriceSubtotal().add(price));
            order.setTaxSubtotal(order.getTaxSubtotal().add(tax));
            order.setGrandTotal(order.getGrandTotal().add(amount));
            order.setStatus(OrderStatus.PENDING.getValue());
        });

        return order;
    }
}
