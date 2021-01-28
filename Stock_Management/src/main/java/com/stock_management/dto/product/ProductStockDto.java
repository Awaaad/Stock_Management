package com.stock_management.dto.product;

import com.stock_management.dto.security.UserDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProductStockDto {
    private Long stockId;
    private Double quantity;
    private Double wholeSalePrice;
    private Double pricePerBox;
    private Double pricePerUnit;
    private Double unitsTotal;
    private LocalDate expiryDate;
    private Double maxUnitsCanBeEntered;
    private UserDto createdBy;
    private Date createdDate;
}
