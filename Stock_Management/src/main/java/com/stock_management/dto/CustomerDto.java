package com.stock_management.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerDto extends AuditDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String address;
    private Integer telephoneNumber;
}
