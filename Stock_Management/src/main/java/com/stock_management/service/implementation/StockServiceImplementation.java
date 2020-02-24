package com.stock_management.service.implementation;

import com.stock_management.entity.Stock;
import com.stock_management.repository.StockRepository;
import com.stock_management.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImplementation implements StockService {
    public final StockRepository stockRepository;

    public StockServiceImplementation(StockRepository stockRepository){
        this.stockRepository = stockRepository;
    }
    @Override
    public List<Stock> findAllStocks() {
        List<Stock> stock = stockRepository.findAll();
        return  stock;
    }
}
