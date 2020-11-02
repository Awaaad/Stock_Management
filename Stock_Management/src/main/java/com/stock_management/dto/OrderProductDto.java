package com.stock_management.dto;

import lombok.Data;

@Data
public class OrderProductDto {
    private Long orderProductId;
    private OrderDto orderDto;
    private ProductDto productDto;
    private String productName;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double totalPrice;
}
