package com.stock_management.service;

import com.stock_management.dto.OrderProductDto;

public interface OrderProductService {
    // POST
    void saveOrderProduct(OrderProductDto orderProductDto);
}
