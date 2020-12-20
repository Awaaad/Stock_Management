package com.stock_management.mapper;

import com.stock_management.dto.ManipulateProductQuantityDto;
import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface ProductMapper {
    ProductDto mapProductEntityToDto (Product productEntity);
    @InheritInverseConfiguration
    Product mapProductDtoToEntity (ProductDto productDto);
}
