package com.stock_management.controller;

import com.stock_management.dto.payment.PaymentDto;
import com.stock_management.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PutMapping("edit-payment")
    public ResponseEntity<String> editDoctor(@RequestBody PaymentDto paymentDto) throws Exception {
        paymentService.editPayment(paymentDto);
        return new ResponseEntity<String>("Payment edited successfully!", HttpStatus.OK);
    }
}
