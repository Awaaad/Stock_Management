package com.stock_management.mapper;

import com.stock_management.dto.stock.StockDto;
import com.stock_management.entity.Stock;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ProductMapper.class
})
public interface StockMapper {
    @Mapping(target = "productDto", source = "product")
    @Mapping(target = "purchaseInvoiceLinesDto", source = "purchaseInvoiceLines")
    @Mapping(target = "orderLinesDto", source = "orderLines")
    StockDto mapStockEntityToDto (Stock stock);
    @InheritInverseConfiguration
    Stock mapStockDtoToEntity (StockDto stockDto);
}
