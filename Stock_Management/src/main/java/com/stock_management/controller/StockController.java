package com.stock_management.controller;

import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.stock.StockDto;
import com.stock_management.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/stock")
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping("save-stocks")
    public ResponseEntity<String> saveStocks(@RequestBody List<StockDto> stocksDto){
        stockService.saveStock(stocksDto);
        return new ResponseEntity<String>("Stocks saved successfully!", HttpStatus.OK);
    }

    @PutMapping("edit-stock")
    public ResponseEntity<String> editStock(@RequestBody StockDto stockDto) throws Exception {
        stockService.editStock(stockDto);
        return new ResponseEntity<String>("Stock edited successfully!", HttpStatus.OK);
    }
}
