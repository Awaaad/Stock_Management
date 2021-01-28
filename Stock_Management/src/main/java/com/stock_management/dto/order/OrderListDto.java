package com.stock_management.dto.order;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderListDto extends PaginationDto {
    List<OrderDto> ordersDto;
}
