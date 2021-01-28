package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue
    @Column(name = "RECEIPT_ID")
    private Long receiptId;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private Double pricePerBox;

    @Column(name = "PRICE_PER_UNIT", nullable = false)
    private Double pricePerUnit;

    @Column(name = "BOXES_ORDERED")
    private Integer boxesOrdered;

    @Column(name = "UNITS_ORDERED")
    private Integer unitsOrdered;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    private UserProfile createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @PrePersist
    protected void prePersist() {
        createdDate = new Date();
    }

}
