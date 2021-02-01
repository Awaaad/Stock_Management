package com.stock_management.mapper;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.entity.Invoice;
import com.stock_management.entity.PurchaseInvoiceLine;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        PurchaseInvoiceLineMapper.class,
        StockMapper.class
})
public interface InvoiceMapper {
    @Mapping(target = "orderDto", source = "order")
    @Mapping(target = "supplierDto", source = "supplier")
    @Mapping(target = "purchaseInvoiceLinesDto", source = "purchaseInvoiceLines")
    @Mapping(target = "orderLinesDto", source = "orderLines")
    @Mapping(target = "doctorDto", source = "doctor")
    @Mapping(target = "customerDto", source = "customer")
    InvoiceDto mapInvoiceEntityToDto (Invoice invoice);
    @InheritInverseConfiguration
    Invoice mapInvoiceDtoToEntity (InvoiceDto invoiceDto);
}
