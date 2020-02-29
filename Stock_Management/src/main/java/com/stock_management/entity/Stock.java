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
public class Stock {
    @Id
    @GeneratedValue
    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "date_added", nullable = false)
    private LocalDateTime dateAdded;

//    @OneToMany(targetEntity =  Product.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
//    private List<Product> products;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
