package com.stock_management.controller;

import com.stock_management.dto.receipt.CustomReceiptDto;
import com.stock_management.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 10000)
@RequestMapping("/receipt")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @GetMapping("invoice-id/{invoiceId}")
    public ResponseEntity<CustomReceiptDto> getReceiptById(@PathVariable Long invoiceId){
        return new ResponseEntity<>(receiptService.findReceiptByInvoiceId(invoiceId), HttpStatus.OK);
    }
}
