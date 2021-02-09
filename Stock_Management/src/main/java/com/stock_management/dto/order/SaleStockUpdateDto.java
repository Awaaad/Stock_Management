package com.stock_management.dto.order;

import com.stock_management.dto.product.ProductDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SaleStockUpdateDto {
    private Long stockId;
    private ProductDto productDto;
    private BigDecimal quantity;
    private Integer unitsPerBox;
    private BigDecimal wholeSalePrice;
    private BigDecimal pricePerBox;
    private BigDecimal pricePerUnit;
    private LocalDate expiryDate;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private BigDecimal total;
}
