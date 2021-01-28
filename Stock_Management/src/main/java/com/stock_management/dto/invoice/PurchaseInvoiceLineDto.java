package com.stock_management.dto.invoice;

import com.stock_management.dto.stock.StockDto;
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
