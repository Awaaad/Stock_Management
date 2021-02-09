package com.stock_management.dto.invoice;

import com.stock_management.dto.stock.StockDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseInvoiceLineDto {
    private Long purchaseInvoiceLineId;
    private StockDto stockDto;
    private BigDecimal wholeSalePrice;
    private BigDecimal oldPricePerBox;
    private BigDecimal pricePerBox;
    private BigDecimal boxesReceived;
}
