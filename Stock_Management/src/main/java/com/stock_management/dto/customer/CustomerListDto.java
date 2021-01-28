package com.stock_management.dto.customer;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class CustomerListDto extends PaginationDto {
    List<CustomerDto> customersDto;
}
