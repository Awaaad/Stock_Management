package com.stock_management.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer telephoneNumber;
}
