package com.stock_management.mapper;

import com.stock_management.dto.ProductDto;
import com.stock_management.entity.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
//    @Mapping(target = "stockDto", source = "productEntity.stock")
    ProductDto mapProductEntityToDto (Product productEntity);
    @InheritInverseConfiguration
    Product mapProductDtoToEntity (ProductDto productDtoDto);
}
