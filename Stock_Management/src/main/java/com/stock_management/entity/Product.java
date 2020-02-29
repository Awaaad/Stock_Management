package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "make_date", nullable = false)
    private Date makeDate;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "supplier")
    private String Supplier;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(targetEntity = Stock.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private Stock stock;
}
