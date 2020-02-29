package com.stock_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StockDto {
    private Long stockId;
    private String stockName;
    private LocalDateTime dateAdded;
    private UserDto user;
}
