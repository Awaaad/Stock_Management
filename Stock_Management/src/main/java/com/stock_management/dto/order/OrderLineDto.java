package com.stock_management.dto.order;

import com.stock_management.dto.stock.StockDto;
import lombok.Data;

@Data
public class OrderLineDto {
    private Long orderLineId;
    private OrderDto orderDto;
    private StockDto stockDto;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double totalPrice;
}
