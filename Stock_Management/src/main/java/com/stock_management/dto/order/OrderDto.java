package com.stock_management.dto.order;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.dto.customer.CustomerDto;
import com.stock_management.entity.UserProfile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long OrderId;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private List<OrderLineDto> orderLinesDto;
    private Double totalPrice;
    private Boolean prescription;
    private UserProfile createdBy;
    private LocalDateTime createdDate;
    private Boolean isNewCustomer;
    private Boolean isNewDoctor;
}
