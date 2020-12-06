package com.stock_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerReceiptDto {
    private Long orderId;
    private String cashierName;
    private String customerName;
    private LocalDateTime orderDate;
    private Double totalPrice;
    private List<ProductQuantityPriceDto> productQuantityPriceDto;
}
