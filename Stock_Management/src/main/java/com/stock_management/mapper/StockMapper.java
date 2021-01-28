package com.stock_management.mapper;

import com.stock_management.dto.StockDto;
import com.stock_management.entity.Stock;
import org.mapstruct.InheritInverseConfiguration;

public interface StockMapper {
    StockDto mapStockEntityToDto (Stock stock);
    @InheritInverseConfiguration
    Stock mapStockDtoToEntity (StockDto stockDto);
}
