package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Integer box;

    @Column(name = "UNITS_PER_BOX")
    private Integer unitsPerBox;

    @Column(name = "UNITS_TOTAL")
    private Integer unitsTotal;

    @Column(name = "PRICE_PER_BOX", nullable = false)
    private Double pricePerBox;

    @Column(name = "PRICE_PER_UNIT")
    private Double pricePerUnit;

    @Column(name = "REQUIRE_PRESCRIPTION")
    private Boolean requirePrescription;

    @ManyToOne(targetEntity = Supplier.class, cascade = CascadeType.MERGE)
    @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "SUPPLIER_ID")
    private Supplier supplier;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}
