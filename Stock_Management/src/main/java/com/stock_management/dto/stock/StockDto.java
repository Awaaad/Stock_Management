package com.stock_management.dto.stock;

import com.stock_management.dto.invoice.PurchaseInvoiceLineDto;
import com.stock_management.dto.order.OrderLineDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.entity.Product;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class StockDto {
    private Long stockId;
    private Product product;
    private Double quantity;
    private Double wholeSalePrice;
    private Double pricePerBox;
    private Double pricePerUnit;
    private LocalDate expiryDate;
    private List<OrderLineDto> orderLinesDto;
    private List<PurchaseInvoiceLineDto> purchaseInvoiceLinesDto;
    private UserDto createdBy;
    private Date createdDate;
}
