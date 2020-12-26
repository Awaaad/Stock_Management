package com.stock_management.repository;

import com.stock_management.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long>, QuerydslPredicateExecutor<PurchaseInvoice> {
}
