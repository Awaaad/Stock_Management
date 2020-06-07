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
import java.util.Set;

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

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "order")
    List<OrderProduct> orderProducts;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "Order_Product",
//            joinColumns = { @JoinColumn (name = "ORDER_ID") },
//            inverseJoinColumns = { @JoinColumn (name = "PRODUCT_ID")}
//    )
//    private List<Product> products;


}


