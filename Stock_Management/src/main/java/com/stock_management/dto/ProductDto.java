package com.stock_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private String category;
    private Date makeDate;
    private Date expiryDate;
    private String Supplier;
    private Double price;
    private Integer quantity;
}
