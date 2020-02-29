package com.stock_management.repository;

import com.stock_management.dto.CountProductsDto;
import com.stock_management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("SELECT p FROM Product p INNER JOIN Stock s WHERE s.stockId =:stockId")
    List<Product> findBystock_stockId(Long stockId);

    Long countBystock_stockId(Long stockId);

}
