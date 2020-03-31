package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "`Order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "CASHIER_NAME", nullable = false)
    private String cashierName;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "QUANTITY_ORDERED_BOX", nullable = false)
    private Integer quantityOrderedBox;

    @Column(name = "QUANTITY_ORDERED_UNIT", nullable = false)
    private Integer quantityOrderedUnit;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "Order_Product",
            joinColumns = { @JoinColumn (name = "ORDER_ID") },
            inverseJoinColumns = { @JoinColumn (name = "PRODUCT_ID")}
    )
    private List<Product> products;
}


