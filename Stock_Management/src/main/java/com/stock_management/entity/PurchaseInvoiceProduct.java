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

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class PurchaseInvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PURCHASE_INVOICE_PRODUCT_ID")
    private Long purchaseInvoiceProductId;

    @ManyToOne
    @JoinColumn(name = "PURCHASE_INVOICE_ID")
    private PurchaseInvoice purchaseInvoice;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "WHOLE_SALE_PRICE", nullable = false)
    private Double wholeSalePrice;

    @Column(name = "OLD_PRICE_PER_BOX", nullable = false)
    private Double oldPricePerBox;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private Double pricePerBox;

    @Column(name = "BOXES_RECEIVED")
    private Integer boxesReceived;
}
