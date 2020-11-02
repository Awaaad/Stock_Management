package com.stock_management.service;

import com.stock_management.dto.OrderProductDto;

import java.util.List;

public interface OrderProductService {
    List<OrderProductDto> findOrderProductsByOrderId(Long orderId);
}
