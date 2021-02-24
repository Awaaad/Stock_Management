package com.stock_management.entity;

import com.stock_management.type.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @ManyToOne(targetEntity = Invoice.class)
    @JoinColumn(name = "INVOICE_ID", referencedColumnName = "INVOICE_ID")
    private Invoice invoice;

    @ManyToOne(targetEntity = Receipt.class)
    @JoinColumn(name = "RECEIPT_ID", referencedColumnName = "RECEIPT_ID")
    private Receipt receipt;

    @Column(name = "AMOUNT_PAID", nullable = false)
    private BigDecimal amountPaid;

    @Column(name = "PREVIOUS_PAYMENT_MODE")
    @Enumerated(EnumType.STRING)
    private PaymentType previousPaymentMode;

    @Column(name = "PAYMENT_MODE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentMode;

    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    private UserProfile createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "USER_ID")
    private UserProfile lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_Modified_DATE")
    private LocalDateTime lastModifiedDate;

    @PrePersist
    protected void prePersist() {
        createdDate = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
        lastModifiedBy = createdBy;
    }

    @PreUpdate
    protected void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }
}
