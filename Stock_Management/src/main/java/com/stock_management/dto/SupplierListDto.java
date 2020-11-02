package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class SupplierListDto extends PaginationDto {
    List<SupplierDto> supplierDtos;
}
