package com.stock_management.service.implementation;

import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.repository.PaymentRepository;
import com.stock_management.repository.UserRepository;
import com.stock_management.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentServiceImplementation(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void editPayment(PaymentDto paymentDto) throws Exception {
        var payment = paymentRepository.findById(paymentDto.getPaymentId()).orElse(null);
        var user = userRepository.findById(paymentDto.getLastModifiedBy().getUserId()).orElse(null);
        if (Objects.nonNull(payment)) {
            payment.setPreviousPaymentMode(payment.getPaymentMode());
            payment.setPaymentMode(paymentDto.getPaymentMode());
            payment.setLastModifiedBy(user);
            payment.setLastModifiedDate(LocalDateTime.now());
            paymentRepository.save(payment);
        } else {
            throw new Exception("payment.not.found");
        }
    }
}
