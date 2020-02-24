package com.stock_management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StockDto {
    private Long stockId;
    private String stockName;
    private Integer quantity;
    private LocalDateTime dateAdded;
    private LocalDateTime dateRemoved;
}
