package com.stock_management.mapper;

import com.stock_management.dto.ProductListDto;
import com.stock_management.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface MultipleSaveProductMapper {
    Product mapSaveMultipleProductsDtoToEntity (ProductListDto productListDto);
}
