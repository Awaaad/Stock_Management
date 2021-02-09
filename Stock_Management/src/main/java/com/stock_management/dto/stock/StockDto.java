package com.stock_management.dto.stock;

import com.stock_management.dto.invoice.PurchaseInvoiceLineDto;
import com.stock_management.dto.order.OrderLineDto;
import com.stock_management.dto.product.ProductDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.dto.shared.AuditDto;
import com.stock_management.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class StockDto extends AuditDto {
    private Long stockId;
    private ProductDto productDto;
    private BigDecimal quantity;
    private Integer unitsPerBox;
    private Integer unitsTotal;
    private BigDecimal wholeSalePrice;
    private BigDecimal pricePerBox;
    private BigDecimal pricePerUnit;
    private LocalDate expiryDate;
    private List<OrderLineDto> orderLinesDto;
    private List<PurchaseInvoiceLineDto> purchaseInvoiceLinesDto;
}
