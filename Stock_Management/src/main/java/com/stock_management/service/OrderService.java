package com.stock_management.service;

import com.stock_management.dto.OrderDto;

import java.util.List;

public interface OrderService {
    // GET
    List<OrderDto> findAllOrders();

    OrderDto findOrderById(Long OrderId);

    // POST
    void saveOrder(OrderDto orderDto);
}
