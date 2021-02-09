package com.stock_management.dto.product;

import com.stock_management.dto.supplier.SupplierDto;
import com.stock_management.dto.shared.AuditDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SaveProductDto extends AuditDto {
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
    private BigDecimal box;
    private BigDecimal unitsTotal;
    private BigDecimal wholeSalePrice;
    private BigDecimal pricePerBox;
    private BigDecimal pricePerUnit;
    private LocalDate expiryDate;
    private Long userId;
}
