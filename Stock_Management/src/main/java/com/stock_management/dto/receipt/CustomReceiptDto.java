package com.stock_management.dto.receipt;

import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.order.SaleStockUpdateDto;
import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.dto.security.UserDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CustomReceiptDto {
    private Long receiptId;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private List<SaleStockUpdateDto> saleStockUpdatesDto;
    private List<PaymentDto> paymentsDto;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private UserDto createdBy;
    private LocalDateTime createdDate;
}
