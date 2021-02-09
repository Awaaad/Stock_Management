package com.stock_management.mapper;

import com.stock_management.dto.order.OrderDto;
import com.stock_management.entity.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OrderLineMapper.class,
        StockMapper.class
})
public interface OrderMapper {
    @Mapping(target = "orderLinesDto", source = "orderLines")
    @Mapping(target = "isNewDoctor", source = "")
    @Mapping(target = "isNewCustomer", source = "")
    @Mapping(target = "customerDto", source = "customer")
    @Mapping(target = "doctorDto", source = "doctor")
    OrderDto mapOrderEntityToDto (Order orderEntity);
    @InheritInverseConfiguration
    Order mapOrderDtoToEntity (OrderDto orderDto);
}