package com.stock_management.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import java.util.List;

@Data
public class ProductDto {
    private Long productId;
    private String productName;
    private String description;
    private String dosage;
    private String category;
    private Double box;
    private Integer unitsPerBox;
    private Double unitsTotal;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Boolean requirePrescription;
    private String slot;
    private SupplierDto supplier;
}