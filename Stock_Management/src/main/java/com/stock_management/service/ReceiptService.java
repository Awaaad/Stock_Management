package com.stock_management.service;

import com.stock_management.dto.receipt.CustomReceiptDto;

public interface ReceiptService {
    CustomReceiptDto findReceiptByInvoiceId(Long receiptId);
}
