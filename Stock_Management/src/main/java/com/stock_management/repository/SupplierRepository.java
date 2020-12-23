package com.stock_management.repository;

import com.stock_management.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SupplierRepository extends JpaRepository<Supplier, Long>, QuerydslPredicateExecutor<Supplier> {
    Supplier findSupplierBySupplierName(String supplierName);
}
