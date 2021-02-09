package com.stock_management.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductQuantityPriceDto {
    private String productName;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private BigDecimal price;
    private BigDecimal pricePerBox;
    private BigDecimal pricePerUnit;
    private Integer unitsPerBox;
}
