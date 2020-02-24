package com.stock_management.service;

import com.stock_management.entity.Stock;

import java.util.List;

public interface StockService {
    List<Stock> findAllStocks();
}
