package com.stock_management.dto;

import lombok.Data;

@Data
public class UpdateStockAmountDto {
    private Long id;
    private Integer amount;
    private Double newPrice;
}
