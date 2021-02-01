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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
    
    @Column(name = "UNITS_PER_BOX")
    private Integer unitsPerBox;

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
