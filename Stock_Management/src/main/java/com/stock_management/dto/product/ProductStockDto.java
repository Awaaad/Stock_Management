package com.stock_management.dto.product;
import com.stock_management.dto.shared.AuditDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductStockDto extends AuditDto {
    private Long stockId;
    private Double quantity;
    private Integer unitsPerBox;
    private Double wholeSalePrice;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Double unitsTotal;
    private LocalDate expiryDate;
    private Double maxUnitsCanBeEntered;
}
