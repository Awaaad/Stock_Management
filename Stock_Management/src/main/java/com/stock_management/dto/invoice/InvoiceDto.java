package com.stock_management.dto.invoice;

import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.order.OrderDto;
import com.stock_management.dto.receipt.ReceiptDto;
import com.stock_management.dto.supplier.SupplierDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.order.OrderLineDto;
import com.stock_management.type.TransactionType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDto {
    private Long invoiceId;
    private String invoiceNumber;
    private TransactionType transactionType;
    private List<PurchaseInvoiceLineDto> purchaseInvoiceLinesDto;
    private List<OrderLineDto> orderLinesDto;
    private Double totalPrice;
    private Double discount;
    private Boolean prescription;
    private OrderDto orderDto;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private SupplierDto supplierDto;
    private UserDto createdBy;
    private Date createdDate;
}
