package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class PurchaseInvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_INVOICE_LINE_ID")
    private Long purchaseInvoiceLineId;

    @ManyToOne
    @JoinColumn(name = "INVOICE_ID")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @Column(name = "WHOLE_SALE_PRICE", nullable = false)
    private BigDecimal wholeSalePrice;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private BigDecimal pricePerBox;

    @Column(name = "BOXES_RECEIVED")
    private BigDecimal boxesReceived;
}
