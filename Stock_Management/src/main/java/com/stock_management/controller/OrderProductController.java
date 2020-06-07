package com.stock_management.controller;

import com.stock_management.dto.OrderProductDto;
import com.stock_management.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/orderProduct")
public class OrderProductController {
    @Autowired
    private final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

//     POST GOES HERE
    @PostMapping("/saveOrderProduct")
    public ResponseEntity<String> saveOrder(@RequestBody OrderProductDto orderProductDto){
        orderProductService.saveOrderProduct(orderProductDto);
        return new ResponseEntity<String>("Order saved successfully!", HttpStatus.OK);
    }
}
