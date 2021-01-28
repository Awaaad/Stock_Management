package com.stock_management.dto;

import lombok.Data;

@Data
public class PurchaseInvoiceLineDto {
    private Long purchaseInvoiceProductId;
    private StockDto stockDto;
    private Double wholeSalePrice;
    private Double oldPricePerBox;
    private Double pricePerBox;
    private Integer boxesReceived;
}
