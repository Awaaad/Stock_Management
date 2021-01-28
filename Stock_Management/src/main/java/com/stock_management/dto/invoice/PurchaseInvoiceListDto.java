package com.stock_management.dto.invoice;

import com.stock_management.dto.shared.PaginationDto;
import lombok.Data;

import java.util.List;

@Data
public class PurchaseInvoiceListDto extends PaginationDto {
    private List<InvoiceDto> purchaseInvoicesDto;
}
