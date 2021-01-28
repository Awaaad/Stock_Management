package com.stock_management.dto.product;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductListDto extends PaginationDto {
    List<ProductDto> productsDto;
}
