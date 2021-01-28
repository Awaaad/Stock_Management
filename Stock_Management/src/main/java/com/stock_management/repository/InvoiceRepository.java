package com.stock_management.repository;

import com.stock_management.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>, QuerydslPredicateExecutor<Invoice> {
}
