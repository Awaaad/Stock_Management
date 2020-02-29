package com.stock_management.controller;

import com.stock_management.dto.StockDto;
import com.stock_management.entity.Stock;
import com.stock_management.repository.StockRepository;
import com.stock_management.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;
    private final StockRepository stockRepository;

    public StockController(StockService stockService, StockRepository stockRepository) {
        this.stockService = stockService;
        this.stockRepository = stockRepository;
    }

    // TESTING
//    @GetMapping("als")
//    public ResponseEntity<Stock>getStocks(){
//        return new ResponseEntity(stockRepository.findAll(), HttpStatus.OK);
//    }
    // GET GOES HERE
    @GetMapping("all")
    public ResponseEntity<List<StockDto>>getAllStocks(){
        return new ResponseEntity<>(stockService.findAllStocks(), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<StockDto>getStockById(@PathVariable Long stockId){
        return new ResponseEntity<>(stockService.findStockById(stockId), HttpStatus.OK);
    }

}
