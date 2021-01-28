package com.stock_management.repository;

import com.stock_management.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByorder_orderId(Long orderId);
}
