package com.stock_management.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SupplierDto extends AuditDto {
    private Long supplierId;
    private String supplierName;
    private String email;
    private Integer telephoneNumber;
    private Integer fax;
    private String address;
}
