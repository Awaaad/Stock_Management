package com.stock_management.entity;

import com.stock_management.type.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.math.BigDecimal;
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

    @Column(name = "PAYMENT_MODE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentMode;

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
