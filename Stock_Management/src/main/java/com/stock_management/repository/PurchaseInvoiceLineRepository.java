package com.stock_management.repository;

import com.stock_management.entity.PurchaseInvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceLineRepository extends JpaRepository<PurchaseInvoiceLine, Long> {
}
