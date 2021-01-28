package com.stock_management.dto;

import lombok.Data;

@Data
public class OrderLineDto {
    private Long orderProductId;
    private OrderDto orderDto;
    private StockDto stockDto;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double totalPrice;
}
