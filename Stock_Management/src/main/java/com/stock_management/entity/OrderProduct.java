package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_PRODUCT_ID")
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "BOXES_ORDERED")
    private Integer boxesOrdered;

    @Column(name = "UNITS_ORDERED")
    private Integer unitsOrdered;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;
}
