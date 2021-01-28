package com.stock_management.dto;
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
