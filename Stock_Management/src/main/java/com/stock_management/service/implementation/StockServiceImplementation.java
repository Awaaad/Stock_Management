package com.stock_management.service.implementation;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.dto.StockDto;
import com.stock_management.entity.Stock;
import com.stock_management.mapper.StockMapper;
import com.stock_management.repository.StockRepository;
import com.stock_management.service.StockService;
import lombok.var;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImplementation implements StockService {
    public final StockRepository stockRepository;
    public final StockMapper stockMapper;

    public StockServiceImplementation(StockRepository stockRepository, StockMapper stockMapper){
        this.stockRepository = stockRepository;
        this.stockMapper = stockMapper;
    }
    @Override
    public List<StockDto> findAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stockMapper::mapStockEntityToDto).collect(Collectors.toList());
    }

    @Override
    public StockDto findStockById(Long stockId) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        var oneStock = stock.orElse(null);
        return stockMapper.mapStockEntityToDto(oneStock);
    }


//    @Override
//    public CountProductsDto countProductsByProductId(Long ProductId) {
//        Integer numberOfProducts = stockRepository.countByproducts_ProductId(ProductId);
//        CountProductsDto countProductsDto = new CountProductsDto();
//        countProductsDto.setNumberOfProducts(numberOfProducts);
//        return countProductsDto;
//    }
}
