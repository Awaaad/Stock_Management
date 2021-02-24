package com.stock_management.service;


import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.type.PaymentType;
import com.stock_management.type.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface InvoiceService {

    void savePurchaseInvoice(SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto);

    InvoiceDto findPurchaseInvoiceById(Long invoiceId);

    PurchaseInvoiceListDto findPurchaseInvoiceByFilters(TransactionType transactionType, PaymentType paymentType, String searchBox, Long userId, Long customerId, LocalDateTime invoiceDateFrom, LocalDateTime invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);

    void bulkPayment(List<InvoiceDto> invoicesDto) throws Exception;
}
