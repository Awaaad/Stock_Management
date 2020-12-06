package com.stock_management.dto;

import lombok.Data;

@Data
public class ProductQuantityPriceDto {
    private String productName;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double price;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Integer unitsPerBox;
}
