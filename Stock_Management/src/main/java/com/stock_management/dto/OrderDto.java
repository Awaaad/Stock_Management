package com.stock_management.dto;
import com.stock_management.entity.UserProfile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long OrderId;
    private UserDto userProfileDto;
    private String customerName;
    private LocalDateTime orderDate;
    private List<OrderProductDto> orderProductDtos;
    private Double totalPrice;
    private Double amountPaid;
    private Double discount;
    private Boolean paid;
    private String paymentMode;
    private Boolean prescription;
    private String doctorName;
}
