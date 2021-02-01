package com.stock_management.dto.order;

import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.payment.PaymentDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SaleTransactionDto {
    private List<SaleStockUpdateDto> saleStockUpdatesDto;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private Boolean isPrescription;
    private Boolean isNewCustomer;
    private Boolean isNewDoctor;
    private Double totalPrice;
    private Double soldAt;
    private List<PaymentDto> paymentsDto;
    private Long userId;
    private Date createdDate;
}
