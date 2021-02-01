package com.stock_management.dto.order;

import com.stock_management.dto.product.ProductDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleStockUpdateDto {
    private Long stockId;
    private ProductDto productDto;
    private Double quantity;
    private Integer unitsPerBox;
    private Double wholeSalePrice;
    private Double pricePerBox;
    private Double pricePerUnit;
    private LocalDate expiryDate;
    private Integer boxesOrdered;
    private Integer unitsOrdered;
    private Double total;
}
