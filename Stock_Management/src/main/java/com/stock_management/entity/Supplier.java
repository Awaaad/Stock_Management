package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

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
