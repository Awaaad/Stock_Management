package com.stock_management.service;
import com.stock_management.dto.PurchaseInvoiceDto;
import com.stock_management.dto.PurchaseInvoiceListDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseInvoiceService {

    void savePurchaseInvoice(PurchaseInvoiceDto purchaseInvoiceDto);

    PurchaseInvoiceDto findPurchaseInvoiceById(Long purchaseInvoiceId);

    List<PurchaseInvoiceDto> findAllPurchaseInvoice();

    PurchaseInvoiceListDto findPurchaseInvoiceByFilters(String supplierName, LocalDateTime invoiceDate, String sortOrder, String sortBy, Integer pageNumber, Integer pageSize);
}
