package com.stock_management.dto.supplier;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class SupplierListDto extends PaginationDto {
    List<SupplierDto> suppliersDto;
}
