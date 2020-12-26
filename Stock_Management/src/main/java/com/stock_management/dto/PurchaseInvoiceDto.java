package com.stock_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseInvoiceDto {
    private Long purchaseInvoiceId;
    private SupplierDto supplierDto;
    private LocalDateTime invoiceDate;
    private List<PurchaseInvoiceProductDto> purchaseInvoiceProductDtos;
}
