package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    @Column(name = "SUPPLIER_ID")
    private Long supplierId;

    @Column(name = "SUPPLIER_NAME", nullable = false)
    private String supplierName;

    @Column(name = "EMAIL", nullable = true)
    private String email;

    @Column(name = "TELEPHONE_NUMBER", nullable = true)
    private Integer telephoneNumber;

    @Column(name = "FAX", nullable = true)
    private Integer fax;

    @Column(name = "ADDRESS", nullable = true)
    private String address;
}
