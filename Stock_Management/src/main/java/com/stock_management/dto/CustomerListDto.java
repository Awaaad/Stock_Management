package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerListDto extends PaginationDto {
    List<CustomerDto> customerDtos;
}
