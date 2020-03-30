package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private String dosage;
    private String category;
    private Integer box;
    private Integer unitsPerBox;
    private Integer unitsTotal;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Boolean requirePrescription;
//    private List<OrderDto> orders;
    private SupplierDto supplier;
}
