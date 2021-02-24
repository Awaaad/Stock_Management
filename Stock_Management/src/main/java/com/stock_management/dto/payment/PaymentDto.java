package com.stock_management.dto.payment;

import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.receipt.ReceiptDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.dto.shared.AuditDto;
import com.stock_management.entity.Invoice;
import com.stock_management.type.PaymentType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PaymentDto extends AuditDto {
    private Long paymentId;
    private InvoiceDto invoiceDto;
    private ReceiptDto receiptDto;
    private BigDecimal amountPaid;
    private PaymentType previousPaymentMode;
    private PaymentType paymentMode;
}
