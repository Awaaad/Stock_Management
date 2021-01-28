package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DOSAGE", nullable = false)
    private String dosage;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "MIN_STOCK_AMOUNT", nullable = false)
    private Integer minStockAmount;

    @Column(name = "UNITS_PER_BOX")
    private Integer unitsPerBox;

    @Column(name = "REQUIRE_PRESCRIPTION", nullable = false)
    private Boolean requirePrescription;

    @Column(name = "SLOT")
    private String slot;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

    @CreatedBy
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    private UserProfile createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @LastModifiedBy
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "USER_ID")
    private UserProfile lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_Modified_DATE")
    private Date lastModifiedDate;

    @PrePersist
    protected void prePersist() {
        createdDate = new Date();
        lastModifiedDate = new Date();
        lastModifiedBy = createdBy;
    }

    @PreUpdate
    protected void preUpdate() {
        lastModifiedDate = new Date();
    }
}
