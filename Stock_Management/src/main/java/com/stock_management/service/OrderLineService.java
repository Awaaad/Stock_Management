package com.stock_management.service;

import com.stock_management.dto.order.OrderLineDto;

import java.util.List;

public interface OrderLineService {
    List<OrderLineDto> findOrderProductsByOrderId(Long orderId);
}
