package com.stock_management.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DoctorDto {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer telephoneNumber;
    private UserDto createdBy;
    private Date createdDate;
    private UserDto lastModifiedBy;
    private Date lastModifiedDate;
}
