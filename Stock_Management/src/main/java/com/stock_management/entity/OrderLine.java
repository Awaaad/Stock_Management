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
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_Line_ID")
    private Long orderLineId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @Column(name = "BOXES_ORDERED")
    private Integer boxesOrdered;

    @Column(name = "UNITS_ORDERED")
    private Integer unitsOrdered;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;
}
