package com.stock_management.repository;

import com.stock_management.entity.Product;
import com.stock_management.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>{
    List<Stock> findStockByProduct_ProductId_OrderByCreatedDateDesc(Long productId);
    List<Stock> findStockByProduct_ProductId_AndQuantityIsGreaterThanOrderByCreatedDateDesc(Long productId, double greaterThan);
}