package com.stock_management.dto;

import lombok.Data;

@Data
public class OrderProductDto {
    private Long orderProductId;
    private OrderDto orderDto;
    private ProductDto productDto;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double totalPrice;
}
