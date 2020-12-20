package com.stock_management.dto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long OrderId;
    private String cashierName;
    private String customerName;
    private LocalDateTime orderDate;
    private List<OrderProductDto> orderProductDtos;
    private Double totalPrice;
    private Double amountPaid;
    private Boolean paid;
}
