package com.stock_management.dto.order;
import com.stock_management.dto.doctor.DoctorDto;
import com.stock_management.dto.invoice.InvoiceDto;
import com.stock_management.dto.security.UserDto;
import com.stock_management.dto.customer.CustomerDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Long OrderId;
    private UserDto userProfileDto;
    private CustomerDto customerDto;
    private DoctorDto doctorDto;
    private Date createdDate;
    private List<OrderLineDto> orderLinesDto;
    private Double totalPrice;
    private Boolean prescription;
    private InvoiceDto invoiceDto;
    private Boolean isNewCustomer;
    private Boolean isNewDoctor;
}
