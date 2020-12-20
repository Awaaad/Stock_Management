package com.stock_management.service;

import com.stock_management.dto.CustomerReceiptDto;
import com.stock_management.dto.MonthlySalesDto;
import com.stock_management.dto.OrderDto;
import com.stock_management.dto.OrderListDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    // GET
    List<OrderDto> findAllOrders();

    OrderDto findOrderById(Long OrderId);

    OrderListDto findListOfOrdersByFilters(String customerName, String cashierName, LocalDateTime orderDateTime, Boolean paid, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    CustomerReceiptDto findCustomerReceiptDetails(Long orderId);

    Double findEODSalesAmount(LocalDate dateTime);

    Double findMonthSalesAmount(LocalDate month, LocalDate year);

    MonthlySalesDto findSalesForEachMonth(LocalDate year);

    // POST
    void saveOrder(OrderDto orderDto);

    // PUT
    void editOrder(OrderDto orderDto);

}

