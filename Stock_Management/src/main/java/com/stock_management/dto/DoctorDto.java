package com.stock_management.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorDto extends AuditDto {
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
