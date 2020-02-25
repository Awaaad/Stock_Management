package com.stock_management.repository;

import com.stock_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    public Integer countAll();
}
