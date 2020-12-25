package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DOSAGE", nullable = false)
    private String dosage;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "BOX", nullable = false)
    private Double box;

    @Column(name = "UNITS_PER_BOX")
    private Integer unitsPerBox;

    @Column(name = "UNITS_TOTAL")
    private Double unitsTotal;

    @Column(name = "OLD_PRICE_PER_BOX", nullable = false)
    private Double oldPricePerBox;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private Double pricePerBox;

    @Column(name = "PRICE_PER_UNIT", nullable = false)
    private Double pricePerUnit;

    @Column(name = "REQUIRE_PRESCRIPTION", nullable = false)
    private Boolean requirePrescription;

    @Column(name = "SLOT")
    private String slot;

    @Column(name = "EXPIRY_DATE")
    private LocalDate expiryDate;

    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    List<OrderProduct> orderProducts;
}
