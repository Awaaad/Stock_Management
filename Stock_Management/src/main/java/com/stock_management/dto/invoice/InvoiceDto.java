package com.stock_management.dto.invoice;

import com.stock_management.dto.doctor.DoctorDto;
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
    private Boolean prescription;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private UserDto createdBy;
    private SupplierDto supplierDto;
    private ReceiptDto receiptDto;
    private Date createdDate;
}
