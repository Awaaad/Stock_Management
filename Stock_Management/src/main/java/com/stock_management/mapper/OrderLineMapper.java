package com.stock_management.mapper;

import com.stock_management.dto.OrderLineDto;
import com.stock_management.entity.OrderLine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {
    @Mapping(source = "orderLineEntity.order", target = "orderDto")
    @Mapping(target = "stockDto", source = "stock")
    OrderLineDto mapOrderLineEntityToDto (OrderLine orderLineEntity);
    @InheritInverseConfiguration
    OrderLine mapOrderLineDtoToEntity (OrderLineDto orderLineDto);
}
