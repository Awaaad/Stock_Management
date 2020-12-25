package com.stock_management.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import java.time.LocalDate;
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
    private Double oldPricePerBox;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Boolean requirePrescription;
    private String slot;
    private LocalDate expiryDate;
    private SupplierDto supplier;
}