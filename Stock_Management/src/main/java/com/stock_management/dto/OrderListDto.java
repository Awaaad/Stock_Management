package com.stock_management.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderListDto extends PaginationDto {
    List<OrderDto> orderDtos;
}
