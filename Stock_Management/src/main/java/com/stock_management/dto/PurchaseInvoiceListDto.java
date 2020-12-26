package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseInvoiceListDto extends PaginationDto {
    private List<PurchaseInvoiceDto> purchaseInvoiceDtos;
}
