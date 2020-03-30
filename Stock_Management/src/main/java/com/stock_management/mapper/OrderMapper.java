package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto mapOrderEntityToDto (Order orderEntity);
    @InheritInverseConfiguration
    Order mapOrderDtoToEntity (OrderDto orderDto);
}