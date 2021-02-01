package com.stock_management.entity;

import com.stock_management.type.PaymentType;
import com.stock_management.type.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Invoice {
    @Id
    @GeneratedValue
    @Column(name = "INVOICE_ID")
    private Long invoiceId;

    @Column(name = "INVOICE_NUMBER", nullable = false)
    private String invoiceNumber;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @OneToMany(mappedBy = "invoice")
    private List<PurchaseInvoiceLine> purchaseInvoiceLines;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;

    @Column(name = "DISCOUNT")
    private Double discount;

    @Column(name = "PRESCRIPTION")
    private Boolean prescription;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private Order order;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")
    private Doctor doctor;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

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
