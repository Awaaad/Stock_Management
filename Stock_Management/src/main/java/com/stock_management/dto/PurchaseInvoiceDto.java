package com.stock_management.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseInvoiceDto {
    private Long purchaseInvoiceId;
    private String InvoiceNumber;
    private UserDto userProfileDto;
    private SupplierDto supplierDto;
    private LocalDateTime invoiceDate;
    private Double total;
    private List<PurchaseInvoiceProductDto> purchaseInvoiceProductDtos;
}
