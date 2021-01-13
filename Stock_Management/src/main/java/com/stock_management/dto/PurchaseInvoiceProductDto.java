package com.stock_management.dto;

import lombok.Data;

@Data
public class PurchaseInvoiceProductDto {
    private Long purchaseInvoiceProductId;
    private ProductDto productDto;
    private Double wholeSalePrice;
    private Double oldPricePerBox;
    private Double pricePerBox;
    private Integer boxesReceived;
}
