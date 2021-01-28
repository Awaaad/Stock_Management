package com.stock_management.dto;

import com.stock_management.entity.Product;
import com.stock_management.entity.UserProfile;
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
