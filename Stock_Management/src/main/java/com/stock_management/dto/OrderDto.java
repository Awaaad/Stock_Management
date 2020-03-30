package com.stock_management.dto;

import com.stock_management.entity.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
public class OrderDto {
    private Long OrderId;
    private String cashierName;
    private String customerName;
    private LocalDateTime orderDate;
    private String productName;
    private Integer quantityOrderedBox;
    private Integer quantityOrderedUnit;
    private List<ProductDto> products;
    private Double totalPrice;
}
