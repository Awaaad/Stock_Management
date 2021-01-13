package com.stock_management.mapper;

import com.stock_management.dto.PurchaseInvoiceDto;
import com.stock_management.entity.PurchaseInvoice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        OrderProductMapper.class,
        ProductMapper.class
})
public interface PurchaseInvoiceMapper {
    @Mapping(target = "userProfileDto", source = "userProfile")
    @Mapping(target = "purchaseInvoiceProductDtos", source = "purchaseInvoiceProducts")
    @Mapping(target = "supplierDto", source = "supplier")
    PurchaseInvoiceDto mapPurchaseInvoiceEntityToDto (PurchaseInvoice purchaseInvoice);
    @InheritInverseConfiguration
    PurchaseInvoice mapPurchaseInvoiceDtoToEntity (PurchaseInvoiceDto purchaseInvoiceDto);
}
