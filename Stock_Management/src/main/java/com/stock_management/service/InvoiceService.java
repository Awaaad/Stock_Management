package com.stock_management.service;


import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;
import com.stock_management.type.TransactionType;

import java.util.Date;

public interface InvoiceService {

    void savePurchaseInvoice(SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto);

    InvoiceDto findPurchaseInvoiceById(Long invoiceId);

    PurchaseInvoiceListDto findPurchaseInvoiceByFilters(TransactionType transactionType, String searchBox, Long userId, Boolean paid, Date invoiceDateFrom, Date invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);
}
