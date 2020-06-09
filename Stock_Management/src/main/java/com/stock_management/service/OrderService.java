package com.stock_management.service;

import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderListDto;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    // GET
    List<OrderDto> findAllOrders();

    OrderDto findOrderById(Long OrderId);

    OrderListDto findListOfOrdersByFilters(String customerName, String cashierName, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    // POST
    void saveOrder(OrderDto orderDto);

    // PUT
    void editOrder(OrderDto orderDto);

}

