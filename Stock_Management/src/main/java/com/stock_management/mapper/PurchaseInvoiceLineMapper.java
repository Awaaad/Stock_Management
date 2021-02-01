package com.stock_management.mapper;

import com.stock_management.dto.invoice.PurchaseInvoiceLineDto;
import com.stock_management.entity.PurchaseInvoiceLine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        StockMapper.class,
        ProductMapper.class
})
public interface PurchaseInvoiceLineMapper {
    @Mapping(target = "oldPricePerBox", source = "")
    @Mapping(target = "stockDto", source = "stock")
    PurchaseInvoiceLineDto mapPurchaseInvoiceLineEntityToDto (PurchaseInvoiceLine purchaseInvoiceLine);
    @InheritInverseConfiguration
    PurchaseInvoiceLine mapPurchaseInvoiceLineDtoToEntity (PurchaseInvoiceLineDto purchaseInvoiceLineDto);
}
