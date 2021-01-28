package com.stock_management.controller;

import com.stock_management.dto.order.OrderLineDto;
import com.stock_management.service.OrderLineService;
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
    private final OrderLineService orderLineService;

    public OrderProductController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @GetMapping("orderId/{orderId}")
    public ResponseEntity<List<OrderLineDto>>getProductsByStockId(@PathVariable Long orderId){
        return new ResponseEntity<>(orderLineService.findOrderProductsByOrderId(orderId), HttpStatus.OK);
    }
}
