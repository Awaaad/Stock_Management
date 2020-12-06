package com.stock_management.controller;

import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.EODSalesAmountDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderListDto;
import com.stock_management.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // GET GOES HERE
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/customerReceipt/{orderId}")
    public ResponseEntity<CustomerReceiptDto> getCustomerReceipt(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.findCustomerReceiptDetails(orderId), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<OrderListDto> getOrdersViaFilter(@RequestParam String customerName, @RequestParam String cashierName, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(orderService.findListOfOrdersByFilters(customerName, cashierName, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getEODSalesAmount")
    public ResponseEntity<EODSalesAmountDto> getEODSalesAmount(@RequestParam("localDate")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate orderDate) {
        return new ResponseEntity<EODSalesAmountDto>(orderService.findEODSalesAmount(orderDate), HttpStatus.OK);
    }


    // POST GOES HERE
    @PostMapping("/saveOrder")
    public ResponseEntity saveOrder(@RequestBody OrderDto orderDto){
        orderService.saveOrder(orderDto);
        return new ResponseEntity<String>("Order saved successfully!", HttpStatus.OK);
    }

    // PUT GOES HERE
    @PutMapping("/editOrder")
    public ResponseEntity editOrder(@RequestBody OrderDto orderDto){
        orderService.editOrder(orderDto);
        return new ResponseEntity<String>("Order edited successfully!", HttpStatus.OK);
    }
}
