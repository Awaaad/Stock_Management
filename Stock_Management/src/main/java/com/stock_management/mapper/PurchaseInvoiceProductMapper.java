package com.stock_management.mapper;

import com.stock_management.dto.PurchaseInvoiceProductDto;
import com.stock_management.entity.PurchaseInvoiceProduct;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OrderProductMapper.class
})
public interface PurchaseInvoiceProductMapper {
    @Mapping(target = "productDto", source = "product")
    PurchaseInvoiceProductDto mapPurchaseInvoiceProductEntityToDto (PurchaseInvoiceProduct purchaseInvoiceProduct);
    @InheritInverseConfiguration
    PurchaseInvoiceProduct mapPurchaseInvoiceProductDtoToEntity (PurchaseInvoiceProductDto purchaseInvoiceProductDto);
}
