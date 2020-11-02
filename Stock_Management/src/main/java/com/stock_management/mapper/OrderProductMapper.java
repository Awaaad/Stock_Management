package com.stock_management.mapper;

import com.stock_management.dto.OrderProductDto;
import com.stock_management.entity.OrderProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ProductMapper.class
})
public interface OrderProductMapper {
    @Mapping(source = "orderProductEntity.product", target = "productDto")
    @Mapping(source = "orderProductEntity.order", target = "orderDto")
    OrderProductDto mapOrderProductEntityToDto (OrderProduct orderProductEntity);
    @InheritInverseConfiguration
    OrderProduct mapOrderProductDtoToEntity (OrderProductDto orderProductDto);
}
