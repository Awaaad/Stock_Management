package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class PurchaseInvoice {
    @Id
    @GeneratedValue
    @Column(name = "PURCHASE_INVOICE_ID")
    private Long purchaseInvoiceId;

    @Column(name = "INVOICE_NUMBER", nullable = false)
    private String invoiceNumber;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

    @Column(name = "INVOICE_DATE", nullable = false)
    private LocalDateTime invoiceDate;

    @Column(name = "TOTAL", nullable = false)
    private Double total;

    @OneToMany(mappedBy = "purchaseInvoice")
    List<PurchaseInvoiceProduct> purchaseInvoiceProducts;
}
