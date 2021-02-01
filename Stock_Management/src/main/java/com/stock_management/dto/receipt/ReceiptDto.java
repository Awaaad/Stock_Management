package com.stock_management.dto.receipt;

import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.security.UserDto;
import lombok.Data;
import java.util.Date;

@Data
public class ReceiptDto {
    private Long receiptId;
    private InvoiceDto invoiceDto;
    private Double totalPrice;
    private Double discount;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private UserDto createdBy;
    private Date createdDate;
}
