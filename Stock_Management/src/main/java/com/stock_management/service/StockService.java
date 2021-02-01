package com.stock_management.service;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.stock.StockDto;
import com.stock_management.entity.Stock;

import java.util.List;

public interface StockService {
    List<Stock> saveStock(List<StockDto> stocksDto);

    // PUT
    void editStock(StockDto stockDto) throws Exception;

}
