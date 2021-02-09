package com.stock_management.dto.stock;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateStockAmountDto {
    private Long id;
    private Integer amount;
    private BigDecimal newPrice;
}
