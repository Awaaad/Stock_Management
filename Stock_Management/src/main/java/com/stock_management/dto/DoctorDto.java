package com.stock_management.dto;

import lombok.Data;

@Data
public class DoctorDto {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer telephoneNumber;
}
