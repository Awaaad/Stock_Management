package com.stock_management.dto;

import com.stock_management.entity.Stock;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import javax.validation.constraints.DecimalMax;
import java.time.LocalDate;
import java.util.List;

@Data
public class ProductDto extends AuditDto {
    private Long productId;
    private String productName;
    private String description;
    private String dosage;
    private String category;
    private Integer minStockAmount;
    private Integer unitsPerBox;
    private Boolean requirePrescription;
    private String slot;
    private SupplierDto supplier;
    private List<ProductStockDto> productStocksDto;
//    private Double box;
//    private Double unitsTotal;
//    private Double wholeSalePrice;
//    private Double oldPricePerBox;
//    private Double pricePerBox;
//    private Double pricePerUnit;
//    private LocalDate expiryDate;
//    private Double maxUnitsCanBeEntered;
}