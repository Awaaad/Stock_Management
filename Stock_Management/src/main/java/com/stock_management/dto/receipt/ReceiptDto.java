package com.stock_management.dto.receipt;

import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.security.UserDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReceiptDto {
    private Long receiptId;
    private InvoiceDto invoiceDto;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private UserDto createdBy;
    private Date createdDate;
}
