package com.stock_management.controller;

import com.stock_management.entity.Stock;
import com.stock_management.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // GET GOES HERE
    @GetMapping("/all")
    public ResponseEntity<List<Stock>>getAllStocks(){
        return new ResponseEntity<>(stockService.findAllStocks(), HttpStatus.ACCEPTED.OK);
    }
}
