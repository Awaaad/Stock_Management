package com.stock_management.service;

import com.stock_management.dto.payment.PaymentDto;

public interface PaymentService {
    void editPayment(PaymentDto paymentDto) throws Exception;
}
