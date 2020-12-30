package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private UserProfile userProfile;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne(targetEntity = Doctor.class)
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "DOCTOR_ID")
    private Doctor doctor;

    @Column(name = "ORDER_DATE", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Double totalPrice;

    @Column(name = "AMOUNT_PAID", nullable = false)
    private Double amountPaid;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @Column(name = "PAYMENT_MODE", nullable = false)
    private String paymentMode;

    @Column(name = "PRESCRIPTION", nullable = false)
    private Boolean prescription;

    @OneToMany(mappedBy = "order")
    List<OrderProduct> orderProducts;
}


