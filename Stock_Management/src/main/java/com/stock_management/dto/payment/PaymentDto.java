package com.stock_management.dto.payment;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.receipt.ReceiptDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.entity.Invoice;
import com.stock_management.type.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PaymentDto {
    private Long paymentId;
    private InvoiceDto invoiceDto;
    private ReceiptDto receiptDto;
    private BigDecimal amountPaid;
    private PaymentType paymentMode;
    private UserDto createdBy;
    private Date createdDate;
}
