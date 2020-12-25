package com.stock_management.mapper;

import com.stock_management.dto.OrderDto;
import com.stock_management.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OrderProductMapper.class
})
public interface OrderMapper {
    @Mapping(target = "orderProductDtos", source = "orderProducts")
    OrderDto mapOrderEntityToDto (Order orderEntity);
    @InheritInverseConfiguration
    Order mapOrderDtoToEntity (OrderDto orderDto);
}