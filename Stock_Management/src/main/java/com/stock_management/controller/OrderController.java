package com.stock_management.controller;

import com.stock_management.dto.customer.CustomerReceiptDto;
import com.stock_management.dto.order.MonthlySalesDto;
import com.stock_management.dto.order.OrderDto;
import com.stock_management.dto.order.OrderListDto;
import com.stock_management.dto.order.SaleTransactionDto;
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

    @GetMapping("all")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @GetMapping("orderId/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("customer-receipt/{orderId}")
    public ResponseEntity<CustomerReceiptDto> getCustomerReceipt(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.findCustomerReceiptDetails(orderId), HttpStatus.OK);
    }

    @GetMapping("filter")
    public ResponseEntity<OrderListDto> getOrdersViaFilter(@RequestParam String customerName, @RequestParam Long userId, @RequestParam("orderDateTimeFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderDateTimeFrom, @RequestParam("orderDateTimeTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderDateTimeTo, @RequestParam Boolean paid, @RequestParam String sortOrder, @RequestParam String sortBy, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return new ResponseEntity<>(orderService.findListOfOrdersByFilters(customerName, userId, orderDateTimeFrom, orderDateTimeTo, paid, sortOrder, sortBy, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("get-eod-sales-amount")
    public ResponseEntity<Double> getEODSalesAmount(@RequestParam("localDate")
                                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate orderDate) {
        return new ResponseEntity<Double>(orderService.findEODSalesAmount(orderDate), HttpStatus.OK);
    }

    @GetMapping("get-month-sales-amount")
    public ResponseEntity<Double> getMonthSalesAmount(@RequestParam("month") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate month, @RequestParam("year") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate year) {
        return new ResponseEntity<Double>(orderService.findMonthSalesAmount(month, year), HttpStatus.OK);
    }

    @GetMapping("get-each-month-sales-amount")
    public ResponseEntity<MonthlySalesDto> getEachMonthSalesAmount(@RequestParam("year") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate year) {
        return new ResponseEntity<MonthlySalesDto>(orderService.findSalesForEachMonth(year), HttpStatus.OK);
    }

    @PostMapping("save-sale-transaction")
    public ResponseEntity<String> saveOrder(@RequestBody SaleTransactionDto saleTransactionDto) throws Exception{
        orderService.saveSaleTransaction(saleTransactionDto);
        return new ResponseEntity<String>("Sale transaction saved successfully!", HttpStatus.OK);
    }

    @PutMapping("edit-order")
    public ResponseEntity<String> editOrder(@RequestBody OrderDto orderDto) throws Exception {
        orderService.editOrder(orderDto);
        return new ResponseEntity<String>("Order edited successfully!", HttpStatus.OK);
    }
}
