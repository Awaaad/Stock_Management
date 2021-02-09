package com.stock_management.dto.product;
import com.stock_management.dto.shared.AuditDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductStockDto extends AuditDto {
    private Long stockId;
    private BigDecimal quantity;
    private Integer unitsPerBox;
    private BigDecimal wholeSalePrice;
    private BigDecimal pricePerBox;
    private BigDecimal pricePerUnit;
    private Integer unitsTotal;
    private LocalDate expiryDate;
    private BigDecimal maxUnitsCanBeEntered;
}
