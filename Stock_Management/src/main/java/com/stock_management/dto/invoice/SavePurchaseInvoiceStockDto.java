package com.stock_management.dto.invoice;

import com.stock_management.dto.stock.StockDto;
import com.stock_management.type.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SavePurchaseInvoiceStockDto {
    private String invoiceNumber;
    private TransactionType transactionType;
    private BigDecimal totalPrice;
    private Long supplierId;
    private List<StockDto> stocksDto;
    private Long userId;
    private Date createdDate;
}
