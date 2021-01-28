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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue
    @Column(name = "STOCK_ID")
    private Long stockId;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;

    @Column(name = "UNITS_TOTAL")
    private Double unitsTotal;

    @Column(name = "WHOLE_SALE_PRICE", nullable = false)
    private Double wholeSalePrice;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private Double pricePerBox;

    @Column(name = "PRICE_PER_UNIT", nullable = false)
    private Double pricePerUnit;

    @Column(name = "EXPIRY_DATE")
    private LocalDate expiryDate;

    @OneToMany(mappedBy = "stock")
    List<OrderLine> orderLines;

    @OneToMany(mappedBy = "stock")
    List<PurchaseInvoiceLine> purchaseInvoiceLines;

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
