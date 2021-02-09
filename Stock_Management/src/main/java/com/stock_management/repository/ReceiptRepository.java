package com.stock_management.repository;

import com.stock_management.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
    Receipt findByInvoice_InvoiceId(Long invoiceId);
}
