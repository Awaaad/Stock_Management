package com.stock_management.mapper;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.entity.Invoice;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceDto mapInvoiceEntityToDto (Invoice invoice);
    @InheritInverseConfiguration
    Invoice mapInvoiceDtoToEntity (InvoiceDto invoiceDto);
}
