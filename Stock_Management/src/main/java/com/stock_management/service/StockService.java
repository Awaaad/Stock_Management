package com.stock_management.service;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.StockDto;
import com.stock_management.entity.Stock;

import java.util.List;

public interface StockService {
    List<StockDto> findAllStocks();

    StockDto findStockById (Long stockId);
}
