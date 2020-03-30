package com.stock_management.dto;

import lombok.Data;

@Data
public class ManipulateProductQuantityDto {
    private Long productId;
    private Integer box;
    private Integer unitsPerBox;
    private Integer unitsTotal;
}
