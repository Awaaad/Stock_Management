package com.stock_management.controller;

import com.stock_management.dto.OrderProductDto;
import com.stock_management.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/order-product")
public class OrderProductController {
    @Autowired
    private final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("orderId/{orderId}")
    public ResponseEntity<List<OrderProductDto>>getProductsByStockId(@PathVariable Long orderId){
        return new ResponseEntity<>(orderProductService.findOrderProductsByOrderId(orderId), HttpStatus.OK);
    }
}
