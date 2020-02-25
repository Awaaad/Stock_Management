package com.stock_management.mapper;

import com.stock_management.dto.StockDto;
import com.stock_management.entity.Stock;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDto mapStockEntityToDto (Stock stockEntity);
    @InheritInverseConfiguration
    Stock mapStockDtoToEntity (StockDto stockDto);
}
