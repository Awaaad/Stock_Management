package com.stock_management.repository;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.entity.Product;
import com.stock_management.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
