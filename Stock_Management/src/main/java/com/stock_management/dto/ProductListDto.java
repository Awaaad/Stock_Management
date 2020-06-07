package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductListDto extends PaginationDto{
    List<ProductDto> productDtos;
}
