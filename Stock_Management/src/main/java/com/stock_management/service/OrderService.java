package com.stock_management.service;

import com.stock_management.dto.customer.CustomerReceiptDto;
import com.stock_management.dto.order.MonthlySalesDto;
import com.stock_management.dto.order.OrderDto;
import com.stock_management.dto.order.OrderListDto;
import com.stock_management.dto.order.SaleTransactionDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    List<OrderDto> findAllOrders();

    OrderDto findOrderById(Long OrderId);

    OrderListDto findListOfOrdersByFilters(String customerName, Long userId, LocalDateTime orderDateTimeFrom, LocalDateTime orderDateTimeTo, Boolean paid, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    CustomerReceiptDto findCustomerReceiptDetails(Long orderId);

    Double findEODSalesAmount(LocalDate dateTime);

    Double findMonthSalesAmount(LocalDate month, LocalDate year);

    MonthlySalesDto findSalesForEachMonth(LocalDate year);

    void saveSaleTransaction(SaleTransactionDto saleTransactionDto) throws Exception;

    void editOrder(OrderDto orderDto) throws Exception;
}

