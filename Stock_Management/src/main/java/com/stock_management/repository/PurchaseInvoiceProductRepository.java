package com.stock_management.repository;

import com.stock_management.entity.PurchaseInvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceProductRepository extends JpaRepository<PurchaseInvoiceProduct, Long> {
}
