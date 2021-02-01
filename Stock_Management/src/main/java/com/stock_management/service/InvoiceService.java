package com.stock_management.service;


import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.invoice.PurchaseInvoiceListDto;
import com.stock_management.dto.invoice.SavePurchaseInvoiceStockDto;

import java.util.Date;

public interface InvoiceService {

    void savePurchaseInvoice(SavePurchaseInvoiceStockDto savePurchaseInvoiceStockDto);

    InvoiceDto findPurchaseInvoiceById(Long invoiceId);

    PurchaseInvoiceListDto findPurchaseInvoiceByFilters(String searchBox, Date invoiceDateFrom, Date invoiceDateTo, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);
}
