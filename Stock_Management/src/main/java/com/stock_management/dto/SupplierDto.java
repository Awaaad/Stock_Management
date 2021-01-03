package com.stock_management.dto;

import lombok.Data;

@Data
public class SupplierDto {
    private Long supplierId;
    private String supplierName;
    private String email;
    private Integer telephoneNumber;
    private Integer fax;
    private String address;
}
